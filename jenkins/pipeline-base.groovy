def server_classes = [
    "base-vm",
    "bastion-vm",
    "postgres-vm"
]

pipeline {
  agent any

  parameters {
        choice name: 'ServerClasses', choices: server_classes, description: ''
        choice name: 'SubClass', choices: ['Nedi', 'Semmatec', 'Norton'], description: ''
  }

  stages{
    stage('Validate Docker') {
      steps {
        sh 'docker info'
      }
    }

    stage('Test Parameters') {
	    steps {
		  script {
			 def message = "ServerClass Name => ${params.ServerClasses}\nSubClass => ${params.SubClass}"
			 echo message
		}
	  }
	}
  }
}
