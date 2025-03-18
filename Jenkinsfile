pipeline {
    agent any
    environment {
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
        AWS_DEFAULT_REGION = "us-east-1"
    }
    stages {
        stage('Checkout SCM'){
            steps{
                script{
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ganeshsraut/tf-jenkins-eks.git']])
                }
            }
        }
        stage('Initializing Terraform'){
            steps{
                script{
                    dir('EKS'){
                        sh 'terraform init'
                    }
                }
            }
        }
        stage('Formatting Terraform Code'){
            steps{
                script{
                    dir('EKS'){
                        sh 'terraform fmt'
                    }
                }
            }
        }
        stage('Validating Terraform'){
            steps{
                script{
                    dir('EKS'){
                        sh 'terraform validate'
                    }
                }
            }
        }
        stage('Previewing the Infra using Terraform'){
            steps{
                script{
                    dir('EKS'){
                        sh 'terraform plan'
                    }
                    input(message: "Are you sure to proceed?", ok: "Proceed")
                }
            }
        }
        stage('Creating/Destroying an EKS Cluster'){
            steps{
                script{
                    dir('EKS') {
                        sh 'terraform $action --auto-approve'
                    }
                    input(message: "Are you sure to proceed?", ok: "Proceed")
                }
            }
        }
        stage('Deploying inventory service Application') {
            steps{
                script{
                    dir('environment/dev') {
                        sh 'aws eks update-kubeconfig --name my-eks-cluster'
                        sh 'kubectl apply -f namespace.yaml'
                        sh 'kubectl apply -f inventor-service/deployment.yaml'
                        sh 'kubectl apply -f inventor-service/service.yaml'
                        sh 'kubectl apply -f inventor-service/configmap.yaml'
                    }
                }
            }
        }
        stage('Deploying Order service Application') {
            steps{
                script{
                    dir('environment/dev') {
                        sh 'aws eks update-kubeconfig --name my-eks-cluster'
                        sh 'kubectl apply -f namespace.yaml'
                        sh 'kubectl apply -f order-service/deployment.yaml'
                        sh 'kubectl apply -f order-service/service.yaml'
                        sh 'kubectl apply -f order-service/configmap.yaml'
                    }
                }
            }
        }
    }
}