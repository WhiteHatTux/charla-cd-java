pipeline {
  agent any
  tools {
    jdk 'jdk8'
  }
  stages {
    stage('clean'){
      steps {
        deleteDir()
      }
    }
    stage('checkout'){
      steps {
        checkout scm
      }
    }
    stage('Create version'){
      steps {
        sh 'mkdir -p build'
        sh './gradlew versionTxt'
      }
    }
    stage('Build') {
      parallel {
        stage ('build app'){
          steps {
            sh './gradlew clean build docker'
          }
        }
        stage ('build nginx-image'){
          steps {
            sh 'cd nginx-build; docker build -t 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx:$(cat ../build/version.txt)'
          }
        }
      }
    }
    stage('test result') {
      steps {
        junit 'build/test-results/test/*.xml'
      }
    }

    //stage('Check database') {
    //  steps {
    //    sh './scripts/checkDatabase.sh'
    //  }
    //}
    //stage('deploy'){
    //  steps {
    //    sh './scripts/deploy.sh prod deploy'
    //  }
  }
}