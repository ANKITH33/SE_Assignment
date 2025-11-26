pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "ankith33/imt2023075-se"
        DOCKER_CREDENTIALS = "dockerhub"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ANKITH33/To_Do_List'
            }
        }

        stage('Build (Maven)') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Test (Maven)') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Docker Build') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:latest ."
            }
        }

        stage('Docker Login & Push') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: env.DOCKER_CREDENTIALS,
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh """
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push ${DOCKER_IMAGE}:latest
                    """
                }
            }
        }
    }

    post {
        always {
            sh 'docker logout || true'
        }
    }
}
