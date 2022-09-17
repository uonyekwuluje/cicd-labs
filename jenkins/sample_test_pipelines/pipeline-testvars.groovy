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

    // Print Parameters from Jenkins Pipeline    
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
