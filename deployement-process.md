## This setup take 1week to configure it from scratch
# 1 Mar - 6 March 2025 everyday 3hr from 10PM to 1:30 AM or 2 PM to 6 PM
## Prerequisite
git
terraform

## Changes to deploy on any system
Add ssh pub key to github to push and pull changes quicky
Configure Vscode to integrate with github personal account

## commands to deploy tf-jekins-k8s
git remote add origin https://github.com/ganeshsraut/tf-jenkins-eks.git
git remote -v

## Terraform deployment - Jenkins
cd Jenkins\ Server
terraform init
terraform validate
terraform plan
terraform apply --auto-approve

# It creates 1.SG jenkins-sg and jenkins-vpc default SG and 2. EC2
# verify ssh connection to jenkins from local


# Troubleshoot ec2-user data not run
cat  /var/log/cloud-init-output.log
found yum install is stuck because -y not provide in userdata

## Jenkins 2.492.2 is installed
## user data location 
cd /var/lib/cloud/instances/i-01e9557370ba1db11

Restart jenkins able to get url

## Jenkins config
## create jenkins  admin account
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

## create jenkins login
jenkins | jenkins@123
http://98.80.168.31:8080/


## setup global configuration
 Add creds for AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY of aws user by using Jenkins Secrete text setting on global configuration

## Configure jenkins pipeline to deploy/destroy EKS cluster
## to apply and deploy add choice parameter in pipeline
## select Poll SCM schedule to every minute by adding * * * * *