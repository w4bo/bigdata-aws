# Big data on cloud

## `Lambda` - Lab 01

## `EMR` - Lab 02

### Running `WordCount`

0. Once created the cluster, rember to allow incoming traffic on port 22
0. Create an EC2 key-pair named `bigdata.pem`
0. Connect to the cluster using SSH
0. Install git and clone this repo on the cluster
0. Build the `emr` project
0. Launch the application

#### Overall code

```
ssh -i ~/bigdata.pem hadoop@{public-master-DNS}
sudo yum install git -y
git clone https://github.com/w4bo/spark-word-count.git
cd spark-word-count
./gradlew
spark-submit --class it.unibo.big.WordCount build/libs/WordCount-all.jar s3://{bucket-name}/inferno.txt
```