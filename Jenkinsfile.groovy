  pipeline {
    agent any

    environment {
    registry = "samarmatoussi/timesheet"
    registryCredential = 'dockerhub'
    dockerImage = 'samarmatoussi/timesheet:8'
    emailRecipient = 'matoussi.samar20@gmail.com'
    }

    tools {
    maven 'maven'
    }

    stages {
    stage('CHECKOUT GIT') {
    steps {
         checkout([$class: 'GitSCM', branches: [[name: '**/SamarMatoussi']], userRemoteConfigs: [[url: 'your_git_repository_url']]])
    }
    }
    
    stage('MVN CLEAN') {
    steps {
    script {
    sh 'mvn clean'
    }
    }
    }
    
    stage('ARTIFACT CONSTRUCTION') {
    steps {
    script {
    sh 'mvn package'
    }
    }
    }

        stage('Sonar Tests') {
            agent {
                docker 'maven:3.8.3-openjdk-17'
            }
            steps {
                script {
                    unstash 'targetfiles'

                    withSonarQube(installationName: 'sonar') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }


        stage('UNIT TESTS') {
    steps {
    script {
    echo 'launching Unit Tests...'
    sh 'mvn test'
    }
    }
    }
    
    stage('PUBLISH TO NEXUS') {
    steps {
    script {
    sh 'mvn deploy'
    }
    }
    }
    
    stage('Building our image') {
    steps {
    script {
    dir('/path/to/your/project') {
    sh "docker build -t $dockerImage ."
    }
    }
    }
    }
    
    stage('Deploy our image') {
    steps {
    script {
    docker.withRegistry('', registryCredential) {
    dockerImage = docker.image(dockerImage)
    dockerImage.push()
    }
    }
    }
    }
    
    stage('Send Email Notification') {
    steps {
  emailext subject: 'Jenkins Build Notification',
                      body: 'The Jenkins pipeline has been completed successfully.',
                      to: emailRecipient
  }
  }
  }
  }
