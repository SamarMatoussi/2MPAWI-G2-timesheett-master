pipeline {
    agent any

    stages {
        
        stage('Checkout') {
            steps {
                script {
                    checkout([$class: 'GitSCM', branches: [[name: 'SamarMatoussi']], userRemoteConfigs: [[url: 'https://github.com/SamarMatoussi/2MPAWI-G2-timesheett-master.git/']]])
                }
            }
        }
    }
}
