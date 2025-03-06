terraform {
  backend "s3" {
    bucket = "cicd-tf-eks"
    key    = "eks/terraform.tfstate"
    region = "us-east-1"
  }
}