#!/bin/bash
export BIGDATA_KEYPAIR=bigdata
chmod 400 ~/${BIGDATA_KEYPAIR}.pem
export BIGDATA_S3BUCKET=aws-bucket-bigdata2021
export BIGDATA_EMRID=$(aws emr list-clusters --active | python3 -c "import sys, json; print(json.load(sys.stdin)['Clusters'][0]['Id'])")
export BIGDATA_EMRDNS=$(aws emr describe-cluster --cluster-id ${BIGDATA_EMRID} | python3 -c "import sys, json; print(json.load(sys.stdin)['Cluster']['MasterPublicDnsName'])")
