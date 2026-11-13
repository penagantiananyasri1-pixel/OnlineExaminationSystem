pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Downloading code from GitHub'
                checkout scm
            }
        }

        stage('Check Tools') {
            steps {
                bat 'java -version'
                bat '"C:/Users/PENAGANTI ANANYA SRI/Downloads/apache-maven-3.9.16/apache-maven-3.9.16/bin/mvn.cmd" -version'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Online Examination System'
                bat '"C:/Users/PENAGANTI ANANYA SRI/Downloads/apache-maven-3.9.16/apache-maven-3.9.16/bin/mvn.cmd" clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing Application'
                bat '"C:/Users/PENAGANTI ANANYA SRI/Downloads/apache-maven-3.9.16/apache-maven-3.9.16/bin/mvn.cmd" test'
            }
        }

        stage('Package') {
            steps {
                echo 'Generating JAR file'
                bat '"C:/Users/PENAGANTI ANANYA SRI/Downloads/apache-maven-3.9.16/apache-maven-3.9.16/bin/mvn.cmd" package -DskipTests'
            }
        }
stage('Check Docker') {
    steps {
        bat 'docker --version'
        bat 'docker ps'
    }
}

        stage('Docker Build') {
            steps {
                echo 'Creating Docker Image'
                bat 'docker build -t online-exam:1.0 .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying using Kubernetes'
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl apply -f service.yaml'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline execution failed!'
        }

        always {
            echo 'CI/CD execution completed!'
        }
    }
}
