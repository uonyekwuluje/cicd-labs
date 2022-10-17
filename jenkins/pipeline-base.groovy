pipeline {
  agent any
  
  stages{
    stage('Validate Docker') {
      steps {
        sh 'docker info'
      }
    }

    stage('Test Parameters') {
	    steps {
		  script {
			 def message = "Stack Name => ${params.StackName}\n"
			 echo message
		}
	  }
	}
  }
}
