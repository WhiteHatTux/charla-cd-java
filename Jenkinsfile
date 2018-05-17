pipeline {
  agent any
  stages {
    stage('clean'){
      deleteDir()
    }
    stage('Build') {
      steps {
        sh './gradlew clean build docker versionTxt'
      }
    }

  }
}