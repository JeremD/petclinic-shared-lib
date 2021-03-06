def call () {
    pipeline {
        agent any
        tools {
            maven 'Maven 3.6.3'
        }

        stages {
            stage('verify') {
                steps {
                    // Vérifier si Maven installé
                    sh 'mvn -v'
                }
            }
            stage('compile') {
                steps {
                    // Run Maven on a Unix agent.
                    sh 'mvn clean package'
                }
            }
            stage('test') {
                steps {
                    sh 'mvn test'
                }
                post {
                    unstable {
                        echo 'Tests non passants'
                    }
                    failure {
                        slackSend channel: 'jenkins-training', color: '#FF0000', message: 'Petclinic Jérémy', teamDomain: 'devinstitut', tokenCredentialId: '29e2c071-d271-40c4-bf58-736d5147f892'
                        discordSend description: 'Failure from Jérémy', footer: '', image: '', link: '', result: 'FAILURE', thumbnail: '', title: 'Petclinic Pipeline', webhookURL: 'https://discordapp.com/api/webhooks/747819422705778738/dHWPHidlNLpiiKftWU84__Ss2LAkws77Swfdk5OWs22qla3hlI1B4zywW8ROg4nAwjRM'
                    }
                    success {
                        junit 'target/surefire-reports/*xml'
                        slackSend channel: 'jenkins-training', color: '#00FF00', message: 'Petclinic Jérémy', teamDomain: 'devinstitut', tokenCredentialId: '29e2c071-d271-40c4-bf58-736d5147f892'
                        discordSend description: 'Success from Jérémy', footer: '', image: '', link: '', result: 'SUCCESS', thumbnail: '', title: 'Petclinic Pipeline', webhookURL: 'https://discordapp.com/api/webhooks/747819422705778738/dHWPHidlNLpiiKftWU84__Ss2LAkws77Swfdk5OWs22qla3hlI1B4zywW8ROg4nAwjRM'
                    }
                }
            }
        }
    }
}
