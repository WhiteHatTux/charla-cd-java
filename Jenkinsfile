pipeline {
  agent any
  stages {
    stage('clean'){
      steps {
        deleteDir()
      }
    }
    stage('Build') {
      steps {
        sh './gradlew clean build docker versionTxt'
      }
    }

  }
}