#!/bin/bash
# export the public DNS of the cluster
export BIGDATA_EMRID=$(aws emr list-clusters --active | python3 -c "import sys, json; print(json.load(sys.stdin)['Clusters'][0]['Id'])")
export BIGDATA_EMRDNS=$(aws emr describe-cluster --cluster-id ${BIGDATA_EMRID} | python3 -c "import sys, json; print(json.load(sys.stdin)['Cluster']['MasterPublicDnsName'])")
echo ${BIGDATA_EMRDNS}
# connect to the clustering using SSH
ssh -i ~/${BIGDATA_KEYPAIR}.pem -ND 8157 hadoop@${BIGDATA_EMRDNS}
# ... or through the AWS CLI
aws emr socks --cluster-id ${BIGDATA_EMRID} --key-pair-file ~/${BIGDATA_KEYPAIR}.pem