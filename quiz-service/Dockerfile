FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/quiz-service.jar quiz-service.jar
ENTRYPOINT ["java", "-jar", "/quiz-service.jar"]