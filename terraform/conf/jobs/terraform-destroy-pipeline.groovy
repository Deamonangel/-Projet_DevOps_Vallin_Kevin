#!groovy

pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '100'))
        ansiColor('xterm')
    }
    stages {
        stage('go to instance workspace folder') {
            steps {
                sh 'cd /var/jenkins_home/workspace/IaC/terraform && terraform destroy --auto-approve'
            }
        }
    }
}
