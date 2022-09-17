pipeline {
  agent {
      label 'base'
  }
  
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
    
    stage('Test Parameters') {
	    steps {
		  script {
			 def message = "Stack Name => ${params.StackName}\nEnvironment => ${params.Environment}\nInstance Type => ${params.InstanceType}"
			 echo message
		}
	  }
	}
  }
}
