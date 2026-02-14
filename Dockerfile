FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="JAGAT"
WORKDIR /gatekeeper-admin
COPY target/gatekeeper-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java" , "-jar" , "app.jar"]