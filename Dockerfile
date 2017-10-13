FROM java:8
MAINTAINER Vignesh KANNAN (vignesh.p.kannan@gmail.com)
ENV SERVICE_NAME 'reviewable'
COPY ${SERVICE_NAME}.jar ${SERVICE_NAME}.jar 
CMD java -jar ${SERVICE_NAME}.jar
