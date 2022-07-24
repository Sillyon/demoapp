pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo 'building project...'
                sh "./mvnw compile"
            }
        }
        stage('Test') {
            steps {
                echo 'running tests...'
                sh "./mvnw test"
            }
        }
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/Sillyon/demoapp']]])
            }
        }fcomak@40.68.140.105
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t fcomak/myrepo:latest .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhub')]) {
                        sh 'docker login -u fcomak -p ${dockerhub}'
                    }
                    sh 'docker push fcomak/myrepo:latest'
                }
            }
        }
        stage('Deploy App on k8s') {
            steps {
                sshagent(['k8s']) {
                    sh "scp -o StrictHostKeyChecking=no springbootapp.yaml fcomak@40.68.140.105:/home/fcomak"
                    script {
                        try{
                            sh "ssh fcomak@40.68.140.105 kubectl create -f ."
                        }catch(error){
                            sh "ssh fcomak@40.68.140.105 kubectl create -f ."
                        }
                    }
                }
            }
        }
    }
}