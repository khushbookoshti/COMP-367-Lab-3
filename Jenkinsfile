pipeline {
    agent any
    environment {
        // Define the Docker image name using your Docker Hub username and the image name
        DOCKER_IMAGE = 'kkoshti6402/my-webapp'
        // Define the tag you want to use for your Docker image
        DOCKER_TAG = 'latest'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/khushbookoshti/Comp-367-Lab-3.git']]])
            }
        }
        stage('Build Maven Project') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
                // Consider archiving the reports or publishing them to a code quality dashboard
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }
        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-kkoshti6402', usernameVariable: 'DOCKER_HUB_USER', passwordVariable: 'DOCKER_HUB_PASS')]) {
                    bat "echo ${env.DOCKER_HUB_PASS} | docker login --username ${env.DOCKER_HUB_USER} --password-stdin"
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-kkoshti6402') {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                    }
                }
            }
        }
    }
    post {
        always {
            bat 'docker rmi -f $(docker images -q ${env.DOCKER_IMAGE}:${env.DOCKER_TAG}) || true'
        }
    }
}
