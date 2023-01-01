FROM eclipse-temurin:17-jammy
WORKDIR /api
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean install
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/api/app.jar"]