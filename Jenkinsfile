pipeline {
  agent any

  environment {
    APP_NAME = 'dadjoke'
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
      environment {
        GITEA_TOKEN = credentials('GITEA_TOKEN')
      }

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

    stage('SonarQube Analysis') {
      environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
      }

      steps {
        sh """
          mvn clean verify sonar:sonar \\
            -Dsonar.projectKey=${APP_NAME} \\
            -Dsonar.host.url=http://192.168.100.20:9001 \\
            -Dsonar.login=\${SONAR_TOKEN}
        """
        waitForQualityGate abortPipeline: true
      }
    }
  }
}
