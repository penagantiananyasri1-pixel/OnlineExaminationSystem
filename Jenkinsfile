
pipeline {

    agent any

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
                echo 'Running application tests'
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Creating executable JAR file'
                bat 'mvn package'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Creating Docker image'
                bat 'docker build -t online-exam:1.0 .'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Loading image into Minikube'
                bat 'minikube image load online-exam:1.0'

                echo 'Deploying application'
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
