pipeline {
  agent {
      label 'base'
  }

  parameters {
    	choice name: 'NewEnvironment', choices: ['stg1', 'operations', 'production', 'stg2', 'stg3'], description: ''

	    choice name: 'NewInstanceType', choices: instance_types, description: ''

	    validatingString(name: 'StackName',
			 defaultValue: '',
			 failedValidationMessage: 'Stack name can only consist of Alphanumeric and dash characters',
			 description: 'The name of the CloudFormation stack to create',
			 regex: '[-a-zA-Z0-9]+')


  }

  options {
	timestamps()
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
