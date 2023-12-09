FROM openjdk:17-alpine

EXPOSE 8888

ADD target/CloudStorageAleks-0.0.1-SNAPSHOT.jar diploma.jar

ENTRYPOINT ["java", "-jar", "diploma.jar"]