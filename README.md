# Big data on cloud

This repo contains two projects (i.e., exercises).

- lab01
- lab02

Projects are built with Gradle, import them into IntelliJ IDEA as Gradle projects. We cannot use Eclipse, since the AWS Toolkit plugin is only available for IntelliJ.

## `Lambda` - lab01

Build a small data pipeline with S3, DynamoDB, and Lambda.

```
lab01
|----> datasets/           # some data for the tests
|----> src/main/java/      # some Lambda functions
|----> src/test/java/      # testing the local behavior of Lambda functions
```

## `EMR` - lab02

Initialize an EMR cluster to submit a Spark Job.

```
lab02
|----> notebooks/          # a simple notework to use on the cluster
|----> src/main/scala/     # a simple WordCount application
|----> src/test/scala/     # testing the local behavior of the Spark Job
|----> scripts/            # Bash script to initialize, connect, terminate the cluster
```

### Running `WordCount`

0. Once created the cluster, remember to allow incoming traffic on port 22
0. Create an EC2 key-pair named `bigdata.pem`
0. Connect to the cluster using SSH
0. Install git and clone this repo on the cluster
0. Build the `emr` project
0. Launch the application
