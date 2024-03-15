FROM openjdk:21-slim

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven

RUN ./build.sh

COPY eureka/target/*.jar eureka.jar

COPY gateway/target/*.jar gateway.jar

COPY room/target/*.jar room.jar

COPY services/target/*.jar services.jar

COPY user/target/*.jar user.jar

COPY booking/target/*.jar booking.jar

EXPOSE 8081

ENTRYPOINT exec run.sh