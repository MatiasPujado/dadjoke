pipeline {
  agent any

  environment {
    APP_NAME = 'dadjoke'
    GITEA_TOKEN = credentials('GITEA_TOKEN')
    BRANCH_NAME = "${BRANCH_NAME}"
  }

  tools {
    jdk 'oracle-jdk23-graalvm'
  }

  stages {
    stage('Initialize'){
      steps {
        script {
          deleteDir()
        }
      }
    }

    stage('Checkout') {
      steps {
        git(
          branch: "${BRANCH_NAME}",
          url: "http://oauth2:${GITEA_TOKEN}@192.168.100.20:3000/Personal_Projects/${APP_NAME}.git"
        )
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }
  }
}
