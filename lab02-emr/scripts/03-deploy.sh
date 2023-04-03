#!/bin/bash
ssh -i ~/${BIGDATA_KEYPAIR}.pem hadoop@${BIGDATA_EMRDNS}
sudo yum install git -y
git clone https://github.com/w4bo/bigdata-aws.git
cd bigdata-aws/lab02-emr
./gradlew
export BIGDATA_S3BUCKET=aws-bucket-bigdata2021
echo $BIGDATA_S3BUCKET
spark-submit --class emr.WordCount build/libs/Lab02-all.jar s3://${BIGDATA_S3BUCKET}/inferno.txt