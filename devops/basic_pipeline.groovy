pipeline{
    agent any
    tools {
        /**This is configured with this exact name in 'Manage Jenkins > Global Tool Configuration > Maven'*/
        maven 'maven_3_6_3'
    }

    stages {

        stage('build'){
            steps{
                echo 'Building............'
                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn clean compile'
                }
            }
        }
        stage('test'){
            steps{
                echo 'Running tests............'
                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn test'
                }
            }
        }

        stage('run'){
            steps{
                echo 'Installing service..........'

                // echo """ 'mvn spring-boot:run' | at now + 1 minutes """
                // sh """ 'nohup mvn spring-boot:run & || true' """
                //sh """ 'nohup mvn spring-boot:run || true &' """

                withMaven(maven: 'maven_3_6_3'){
                    sh 'mvn install'
                }

            }
        }



        stage('deploy'){
            steps{
                echo 'Deployment in progress............'
                sh """ chmod 777 /Users/ankur/.jenkins/workspace/rest_service_dsl_pipeline/target/RestService-0.0.1-SNAPSHOT.jar """
                //sh """ 'java -jar RestService-0.0.1-SNAPSHOT.jar' """

                withMaven(maven: 'maven_3_6_3'){
                    sh """ nohup mvn spring-boot:run || true"""
                    sh """ echo true """
                }

            }
        }

    }
}

