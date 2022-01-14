#!groovy

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        TEST = 'TEST'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '100'))
        ansiColor('xterm')
    }
    stages {
        stage('Job describ') {
            steps {
                script {
                    println('Env var: ' + env.TEST)
                    sh 'java --version'
                    sh 'mvn --version'
                    sh 'python3 --version'
                    currentBuild.displayName = "#${BUILD_NUMBER} ${params.PARAM1}"
                }
            }
        }
        stage('Job git clone') {
            steps {
                git branch: "master",
                        url: 'https://github.com/Ozz007/jobs1.git'
            }
        }
        stage('Job compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('test unitaire') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('packages') {
            steps {
                sh 'mvn package'
            }
        }
        stage('test integration') {
            when {
                expression { params.SKIP_TESTS == false }
            }
            steps {
                sh 'mvn verify'
            }
        }
        stage('rename Jar') {
            steps {
                script{
                    sh "mv jobs1-ws/target/jobs1-ws-1.0-SNAPSHOT.jar jobs1-${params.VERSION}-${params.VERSION_TYPE}.jar"
                }
            }
        }
    }
}
