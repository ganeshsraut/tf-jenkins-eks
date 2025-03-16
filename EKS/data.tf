data "aws_availability_zones" "azs" {
name = module.eks.cluster_name
depends_on = [ module.eks ]
}