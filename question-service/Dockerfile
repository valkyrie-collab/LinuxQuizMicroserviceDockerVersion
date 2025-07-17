FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/question-service.jar question-service.jar
ENTRYPOINT ["java", "-jar", "/question-service.jar"]