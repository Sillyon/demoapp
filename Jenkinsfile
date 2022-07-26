pipeline {
    agent any
    stages {
        stage('Checkout GitHub Source') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/Sillyon/demoapp']]])
            }
        }
        stage("Build Project") {
            steps {
                echo 'cleaning, compiling, and installing project...'
                sh "./mvnw clean install"
            }
        }
        stage('Run Tests') {
            steps {
                echo 'running tests...'
                sh "./mvnw test"
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t fcomak/myrepo:latest .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pwd', usernameVariable: 'uname')]) {
                        sh "docker login -u ${uname} -p ${pwd}"
                    }
                    sh 'docker push fcomak/myrepo:latest'
                }
            }
        }
        stage('Deploy App on K8S') {
            steps {
                script {
                    withKubeConfig([credentialsId: 'mykubeconfig']) {
                        sh 'sed -ie "s/THIS_STRING_IS_REPLACED_DURING_BUILD/$(date)/g" myweb.yaml'
                        sh 'kubectl apply -f myweb.yaml'
                    }
                }
            }
        }
    }
}