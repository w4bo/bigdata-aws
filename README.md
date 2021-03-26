# Big data on cloud


## Run `WordCount`

```
  ssh -i ~/bigdata.pem hadoop@{public-master-DNS}
  sudo yum install git -y
  git clone https://github.com/w4bo/spark-word-count.git
  cd spark-word-count
  ./gradlew
  spark-submit --class it.unibo.big.WordCount build/libs/WordCount-all.jar s3://{bucket-name}/inferno.txt
```