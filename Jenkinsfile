pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'online-exam:1.0'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Downloading source code from GitHub'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Online Examination System'
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing Online Examination System'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application into JAR'
                bat 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker Image'
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application using Kubernetes'
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl apply -f service.yaml'
            }
        }
    }

    post {
        success {
            echo 'Online Examination System deployed successfully!'
        }

        failure {
            echo 'Pipeline execution failed!'
        }

        always {
            echo 'CI/CD Pipeline execution completed.'
        }
    }
}

