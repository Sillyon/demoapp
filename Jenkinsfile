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
    }
}