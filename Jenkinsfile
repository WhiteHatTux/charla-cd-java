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
        sh './gradlew versionTxt'
      }
    }
    stage('Build') {
      parallel {
        stage('build app') {
          steps {
            sh './gradlew build docker'
          }
        }
        stage('build nginx-image') {
          steps {
            sh 'cd nginx-build; docker build -t 295295069944.dkr.ecr.eu-central-1.amazonaws.com/charla-cd-nginx:$(cat ${WORKSPACE}/build/version.txt) .'
          }
        }
      }
    }
    stage('test result') {
      steps {
        junit 'build/test-results/test/*.xml'
      }
    }
    stage('test startup'){
      steps {
        sh '${WORKSPACE}/scripts/deploy.sh dev deploy'
        sh 'echo wait 50 seconds for server to come up'
        sh 'sleep 50'
        sh 'curl http://localhost/actuator/health | grep UP || echo server was not up after 50 seconds, something went wrong; exit 1'
      }
    }
    stage('deploy a produccion'){
      when {
        branch 'master'
      }
      steps {
        sh '${WORKSPACE}/scripts/deploy.sh prod deploy'
      }
    }
  }
  tools {
    jdk 'jdk8'
  }
}