module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = "eks_cluster_vpc"
  cidr = var.vpc_cidr

  azs             = data.aws_availability_zones.azs.names
  public_subnets  = var.public_subnets
  private_subnets = var.private_subnets

  enable_dns_hostnames = true
  enable_nat_gateway   = true
  single_nat_gateway   = true

  tags = {
    "kubernetes.io/cluster/my-eks-cluster" = "shared"
  }

  public_subnet_tags = {
    "kubernetes.io/cluster/my-eks-cluster" = "shared"
    "kubernetes.io/role/elb"               = 1
  }

  private_subnet_tags = {
    "kubernetes.io/cluster/my-eks-cluster" = "shared"
    "kubernetes.io/role/private-elb"      = 1
  }

}

module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  version         = "~> 20.31"
  cluster_name    = "my-eks-cluster"
  cluster_version = "1.31"

  cluster_endpoint_public_access = true

  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.private_subnets

  eks_managed_node_groups = {
    nodes = {
      min_size     = 1
      max_size     = 3
      desired_size = 2

      instance_type = var.instance_type
      key_name      = "ganesh-personal"
    }
  }

  tags = {
    Environment = "dev"
    Terraform   = "true"
    system      = "cloudops"
    owner       = "ganesh.raut@talentica.com"
  }
}

resource "aws_eks_access_entry" "example" {
  cluster_name      = "my-eks-cluster"
  principal_arn     = "arn:aws:iam::590184134827:user/ganesh.raut@talentica.com"
  type              = "STANDARD"
}

resource "aws_eks_access_policy_association" "example" {
  cluster_name  = "my-eks-cluster"
  policy_arn    = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSClusterAdminPolicy"
  principal_arn = "arn:aws:iam::590184134827:user/ganesh.raut@talentica.com"

  access_scope {
    type       = "cluster"
   
  }
}