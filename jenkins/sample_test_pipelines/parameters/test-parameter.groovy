pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'echo "Test Build"'
      }
    }

    stage ('Invoke_pipelineA') {
        steps {
            build job: 'pipeparms', 
            parameters: [
            string(name: 'PARAMETER_01', value: "ONE")
            ],
            wait: false
        }
    }

    stage('End') {
        steps {
            echo 'Bye'
        }
    }
  }
}
