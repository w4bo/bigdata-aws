#!/bin/bash
# connect to the clustering using SSH
ssh -i ~/${BIGDATA_KEYPAIR}.pem hadoop@${BIGDATA_EMRDNS}
sudo yum install git -y
git clone https://github.com/w4bo/bigdata-aws.git
cd bigdata-aws/lab02-emr
./gradlew
spark-submit --class emr.WordCount build/libs/WordCount-all.jar s3://${BIGDATA_S3BUCKET}/inferno.txt