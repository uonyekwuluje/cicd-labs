def instance_types = [
    "bastion",
    "ossec_server",
    "ossec_agent"
]

pipeline {
  agent {
      label 'base'
  }
  
  
  stages{
    stage('Test Parameters') {
	    steps {
		  script {
			 def message = "AWS StackName => ${params.StackName}\nEnvironment => ${params.Environment}"
			 echo message
		}
	  }
	}
  }
}
