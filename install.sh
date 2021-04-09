#!/bin/bash
set -e
sudo apt update && sudo apt upgrade -y
sudo apt install -y git vim openjdk-8-jdk yakuake apt-transport-https ca-certificates curl gnupg lsb-release
# configure docker
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list 
sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli
sudo usermod -aG docker ${USER}
sudo service docker restart
echo ${USER}
sudo docker ps
# install AWS CLI
# sudo snap install intellij-idea-ultimate --classic
sudo snap install intellij-idea-community --classic
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
# install AWS SAM CLI
wget https://github.com/aws/aws-sam-cli/releases/latest/download/aws-sam-cli-linux-x86_64.zip
unzip aws-sam-cli-linux-x86_64.zip -d sam-installation
sudo ./sam-installation/install
# check the versions
git clone https://github.com/w4bo/bigdata-aws
aws --version
sam --version
