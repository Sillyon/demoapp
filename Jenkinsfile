pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                // git 'https://github.com/Sillyon/demoapp.git'
                echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
                sh "./mvnw spring-boot:run"
            }
        }
    }
}