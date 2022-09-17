def instance_types = [
    "bastion",
    "ossec_server",
    "ossec_agent"
]

pipeline {
  agent {
      label 'base'
  }
  
  parameters {
	choice choices: ['production', 'uat', 'dev'], description: '', name: 'Environment'
	string(name: 'StackName',
	       trim: true,
	       description: 'Enter Stack.')
    validatingString(name: 'StackName',
			 defaultValue: '',
			 failedValidationMessage: 'Stack name can only consist of Alphanumeric and dash characters',
			 description: 'The name of the CloudFormation stack to create',
			 regex: '[-a-zA-Z0-9]+')
  }
  
  stages{
    stage('Test Parameters') {
	    steps {
		  script {
			 def message = "AWS StackName => ${params.StackName} in Environment => ${params.Environment}"
			 echo message
		}
	  }
	}
  }
}
