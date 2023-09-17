FROM maven:3.8.4-openjdk-11 AS build

WORKDIR /kitchen

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /kitchen/target/kitchen-0.0.1-SNAPSHOT.jar .
# Specify the command to run your application
CMD ["java", "-jar", "kitchen-0.0.1-SNAPSHOT.jar"]