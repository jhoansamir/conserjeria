# Etapa de construcción
FROM gradle:8.3.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Imagen final
FROM adoptopenjdk/openjdk17:alpine-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/javalin-application.jar

# ENTRYPOINT para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/javalin-application.jar"]