pipeline {
    agent any
    tools {
        maven 'Maven' // Configuramos la herramienta Maven
    }
    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/andreshiguitaperez/Actividad3.git']]])
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package' // Se compila y construye el proyecto sin ejecutar las pruebas
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Se ejecutan las pruebas unitarias
            }
        }
	    stage('SonarQube analysis'){
            steps{
                withSonarQubeEnv('sonarExample'){
                    sh "mvn package sonar:sonar -Dsonar.sources=src/main -Dsonar.exclude=src/test/** -Dmaven.test.failure.ignore=true"
                }
            }
        }
        stage('Quality Gate') {
          steps {
                sleep 60
                waitForQualityGate abortPipeline: true
            }
        }
    }
}
