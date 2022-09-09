def get_distribution_id() {
    if (params.Environment == 'uat') {
	   return "blog.infracid.com"
    } else {
	   return "blog.${params.Environment}.infracid.com"
    }
}


pipeline {
  agent {
      label 'base'
  }
  
  environment {
     MABL_API_KEY = credentials('mabl-api-key')
     MABL_ENV_ID = credentials('mabl-env-id')
     MABL_APP_ID = credentials('mabl-app-id')
  }

  parameters {
	choice choices: ['production', 'uat', 'dev'], description: '', name: 'Environment'
	string(name: 'Branch',
	       trim: true,
	       description: 'Enter Branch.')
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
			 def message = "Branch => ${params.Branch} in Environment => ${params.Environment}"
			 echo message
		}
	  }
	}
	
	// look into environment variables
	stage("Checkout application") {
	    steps {
		  script {
		    def url = 'git@github.com:uonyekwuluje/cicd-labs.git';

		    def branch = params.Branch
		    if (branch =~ /^\d+\.\d+/) {
			branch = "refs/tags/" + branch
		    }
		    checkout([$class: 'GitSCM',
			      branches: [[name: branch]],
			      userRemoteConfigs: [[credentialsId: "GIT-SSH-KEY", url: url]]])
		}
	  }
	}
	
	// look into ${env.WORKSPACE}, ${env.JENKINS_HOME}
	stage("Tesh Checkout") {
	    steps {
		  sh "ls -la"
	  }
	}
	
	
	// look into environment variables
    stage("mabl UI Test") {
	    steps {
		   sh("curl -s https://api.mabl.com/events/deployment -u 'key:$MABL_API_KEY' -H 'Content-Type:application/json' -d '{\"environment_id\":\"$MABL_ENV_ID\",\"application_id\":\"$MABL_APP_ID\"}'")
	  }
	}
	
  }
}
