FROM gradle:jdk17-corretto AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/agendador-tarefas.jar"]


