pipeline {
    agent any
    tools {
        maven '3.6.3'
    }
    stages {
        stage("Build") {
            steps {
                // git 'https://github.com/Sillyon/demoapp.git'
                echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
                sh "mvn compile"
            }
        }
        stage('Test') {
            steps {
                echo 'running tests...'
                sh "mvn test"
            }
        }
    }
}