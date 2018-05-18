FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
ARG JAR_VERSION_BUILD
ARG JAR_FILE
ENV JAR_VERSION=${JAR_VERSION_BUILD}
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
