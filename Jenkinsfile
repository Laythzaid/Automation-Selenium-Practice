pipeline {
  agent any

  stages {

    stage('Checkout') {
      steps {
        git 'https://github.com/Laythzaid/Automation-Selenium-Practice.git'
      }
    }

    stage('Build') {
      steps {
        bat 'mvn clean compile'
      }
    }

    stage('Run Tests') {
      steps {
        bat 'mvn test'
      }
    }

  }
}