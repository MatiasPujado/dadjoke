pipeline {
  agent any

  environment {
    APP_NAME = 'dadjoke'
    BRANCH_NAME = "${env.BRANCH_NAME ?: 'main'}"
    JAVA_HOME = "${tool 'oracle-jdk23-graalvm'}"
  }

  tools {
    jdk 'oracle-jdk23-graalvm'
  }

  stages {
    stage('Initialize') {
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
        script {
          def branchToCheckout = env.CHANGE_BRANCH ?: env.BRANCH_NAME

          git(
            url: "http://oauth2:${GITEA_TOKEN}@192.168.100.20:3000/Tux_Team/${APP_NAME}.git",
            credentialsId: 'gitea-credentials-admin',
            branch: branchToCheckout
          )
        }
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests'
      }
    }

    stage('Unit Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('SonarQube Analysis') {
      environment {
        SONAR_TOKEN = credentials('SONAR_TOKEN')
      }

      steps {
        withSonarQubeEnv('Homelab SonarQube') {
          sh """
            mvn clean verify sonar:sonar \
              -Dsonar.projectKey=${APP_NAME} \
              -Dsonar.host.url=http://192.168.100.20:9001 \
              -Dsonar.login=${SONAR_TOKEN}
          """
        }

        script {
          timeout(time: 1, unit: 'HOURS') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
          }
        }
      }
    }
  }
}
