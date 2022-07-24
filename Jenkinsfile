pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo 'building project..'
                sh "./mvnw spring-boot:run"
            }
        }
    }
}