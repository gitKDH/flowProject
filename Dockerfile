FROM openjdk:17

ARG JAR_FILE=/build/libs/flowProject-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} flowProject.jar

ENTRYPOINT ["java","-jar","/flow.jar"]