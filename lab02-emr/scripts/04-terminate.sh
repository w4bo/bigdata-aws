#!/bin/bash
aws emr modify-cluster-attributes --cluster-id ${BIGDATA_EMRID} --no-termination-protected
aws emr terminate-clusters --cluster-ids ${BIGDATA_EMRID}