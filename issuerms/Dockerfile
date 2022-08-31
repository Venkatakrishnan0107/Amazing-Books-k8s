FROM openjdk:8
EXPOSE 8082
ADD target/issue-service.jar issue-service.jar
ENTRYPOINT ["java","-jar","/issue-service.jar"]