pipeline {
  agent {
    node {
      label 'centos-docker-build'
    }

  }
  stages {
    stage('clean') {
      steps {
        deleteDir()
        sh 'docker stack rm charlacd || exit 0'
      }
    }
    stage('checkout') {
      steps {
        checkout scm
      }
    }
    stage('Create version') {
      steps {
        sh 'mkdir -p build'
        sh './gradlew versionTxt --no-daemon'
      }
    }
    stage('Prep environment'){
      steps {
        sh '$(aws ecr get-login --no-include-email)'
      }
    }
    stage('Build') {
      parallel {
        stage('build app') {
          steps {
            sh './gradlew build docker --no-daemon --stacktrace'
            jacoco()
            sh 'docker push 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-demo:$(cat ${WORKSPACE}/build/version.txt)'
          }
          post {
            always {
              junit 'build/test-results/test/*.xml'
            }
          }
        }
        stage('build nginx-image') {
          steps {
            sh 'cd nginx-build; docker build -t 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx:$(cat ${WORKSPACE}/build/version.txt) .'
            sh 'docker push 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx:$(cat ${WORKSPACE}/build/version.txt)'
            sh 'cd nginx-build/nginx-prod-extension/; docker build -t 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx-ssl:$(cat ${WORKSPACE}/build/version.txt) .'
            sh 'docker push 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx-ssl:$(cat ${WORKSPACE}/build/version.txt)'
          }
        }
      }
    }
    stage('test startup'){
      steps {
        sh 'chmod +x ${WORKSPACE}/scripts/remove-service.sh'
        sh '${WORKSPACE}/scripts/deploy.sh dev deploy'
        sh '''set +e
            count=0
            healthSuccess=0
            while [ $count -le 20 ]
            do
                count=$(( $count +1 ))
                sleep 5
                curl -o upEndpoint http://$(curl http://169.254.169.254/latest/meta-data/local-ipv4)/actuator/health
                cat upEndpoint | grep "UP"
                apisuccess=`echo $?`
                if [[ $apisuccess -eq 0 ]]; then
                    healthSuccess=1
                    echo server was not up after 100 seconds, something went wrong
                    break;
                fi
            done;
            if [ $healthSuccess -eq 1 ]; then
                echo "server started successfully"
                ${WORKSPACE}/scripts/remove-service.sh || echo some errors stopping the services
            else
                echo "could not get success on master health"
                docker service logs charlacd_db
                docker service logs charlacd_app
                docker service logs charlacd_web_nossl
                ${WORKSPACE}/scripts/remove-service.sh || echo some errors stopping the services
                exit 1
            fi
        '''
      }
    }
    stage('deploy to production'){
      when {
        branch 'master'
      }
      steps {
        sh '${WORKSPACE}/scripts/deploy.sh prod deploy'
      }
      post {
        failure {
          mail bcc: '', body: 'Something went wrong and deployment to production failed. Please check the jenkins log and the server', cc: '', from: '', replyTo: '', subject: 'production deployment failed', to: 'chri_ti1991@yahoo.de'
        }
      }
    }
    stage('verify installation'){
      steps{
        sh 'sleep 30'
        sh '''
        result=$(curl "https://demo-charla.ctimm.de/math/add?first=5&second=56")
        if [[ "$result" = "61" ]]; then
            echo deployment to production was successful
        else
            echo production is broken
            mail bcc: '', body: 'Something went wrong and deployment to production failed. Please check the jenkins log and the server', cc: '', from: '', replyTo: '', subject: 'production deployment failed', to: 'chri_ti1991@yahoo.de'
            exit 1
        fi
        '''
      }
    }


  }
  tools {
    jdk 'jdk8'
  }
}