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
        stage('Nexus') {
            steps {
                // Upload the JAR to Nexus
                nexusArtifactUploader artifacts: [
                    [artifactId: 'tour-hero-api',
                     classifier: '',
                     file: 'target/tour-hero-api-0.0.1-SNAPSHOT.war',
                     type: 'war']
                ],
                credentialsId: 'nexusCredentials',
                groupId: 'co.udea.hero.api',
                nexusUrl: 'efe5-181-128-31-248.ngrok-free.app',
                nexusVersion: 'nexus3',
                protocol: 'https',
                repository: 'app-prueba',
                version: '0.0.1-SNAPSHOT'
            }
        }
    }
}
