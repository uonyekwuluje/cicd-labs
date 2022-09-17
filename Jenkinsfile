pipeline {
  agent any
  
  stages{
    stage('Validate Docker') {
      steps {
        sh 'docker info'
      }
    }
    stage('Test Directory'){
      steps {
        sh 'ls -la'
      }
    }
  }
}
