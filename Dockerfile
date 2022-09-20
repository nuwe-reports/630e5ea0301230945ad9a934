FROM maven:3.8.2-eclipse-temurin-17
ENV APP_HOME=.
WORKDIR $APP_HOME
EXPOSE 8080
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run