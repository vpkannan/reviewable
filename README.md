# Reviewable
Product Review Engine

[![Build Status](http://ec2-34-215-128-136.us-west-2.compute.amazonaws.com:8080/buildStatus/icon?job=reviewable/master)](http://ec2-34-215-128-136.us-west-2.compute.amazonaws.com:8080/job/reviewable/job/master/)
[![Quality Gate](http://ec2-34-215-128-136.us-west-2.compute.amazonaws.com:9000/api/badges/gate?key=vpkannan:reviewable_master-PUFCJY6QXGJCRTEJ3GHVWJHRIFXVKX7SBBH6CBVXB2OZVN4TLSCQ)](http://ec2-34-215-128-136.us-west-2.compute.amazonaws.com:9000/dashboard?id=vpkannan%3Areviewable_master-PUFCJY6QXGJCRTEJ3GHVWJHRIFXVKX7SBBH6CBVXB2OZVN4TLSCQ)


Reviewable is a Product Review Engine constructed using Spring Boot framework. It is containerized in Docker and uses docker-compose for deployment. MongoDB is used for data persistence. 

Reviewable exposes REST API to 
 - Create a Product
 - Add Reviews to the Product
 - Retrieve details of a Product
 - Retrieve the reviews for a given Product
 
Product also contains the average product rating based on the reviews. The updated average product rating is computed as and when Reviews are added.

REST API documentation can be found in the Swagger definition in src/main/resources/api/reviewable.yaml

## Using the Reviewable Application

Docker is used to containerize the Reviewable application and so, build and deploy are straightforward. 

IMPORTANT: These steps assume that Docker and Docker-compose are installed on your environment. 
 - If you do not have Docker installed, follow the instructions these instructions to install Docker - [Docker Installation Guide](https://docs.docker.com/engine/installation)
 - Follow these instructions to install docker-compose - [Docker-compose Installation Guide](https://docs.docker.com/compose/install)



You can choose one of the two options to spin up the application in your local environment. 
 1. Pull Images from Docker Hub
 2. Clone, Build and Deploy

Let's see how each method can be done.

#### 1. Pull Images from Docker Hub

The project contains a docker-compose file to directly pull images that are published to the Docker Hub and deploy it on your local environment. This can be done with a simple step

`docker-compose -f docker-compose-aws.yml up -d`

This will pull both Reviewable and MongoDB images and deploy them as Docker containers in your environment.

#### Clone, Build and Deploy

The alternate approach is to clone the code from GIT, build and deploy it in your environment.

Follow these steps to clone, build and deploy.

1. Clone the GIT project to your environment.
    
    `git clone git@github.com:vpkannan/reviewable.git`

2. (Optional step) Get into the reviewable project root directory and build the project
    
    `./gradlew build`
    
3. Create the docker container based on the dockerfile
    
    `./gradlew buildDocker`
    
4. Deploy the project
    
    `docker-compose up -d`
    
This pull MongoDB and deploys it along with the Reviewable image we just built. 


