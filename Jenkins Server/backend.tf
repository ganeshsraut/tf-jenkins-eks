terraform {
  backend "s3" {
    bucket = "cicd-tf-eks"
    key    = "jenkins/terraform.tfstate"
    region = "us-east-1"
  #  assume_role = {
  #  role_arn = "arn:aws:iam::PRODUCTION-ACCOUNT-ID:role/Terraform"
  #  }

  }
}