FROM openjdk:21-slim

WORKDIR /app

COPY . .

RUN apt-get update

RUN apt-get install -y maven

RUN mvn clean package -DskipTests=true -f eureka/pom.xml \
    && mvn clean package -DskipTests=true -f gateway/pom.xml \
    && mvn clean package -DskipTests=true -f room/pom.xml \
    && mvn clean package -DskipTests=true -f services/pom.xml \
    && mvn clean package -DskipTests=true -f users/pom.xml \
    && mvn clean package -DskipTests=true -f booking/pom.xml

RUN mv eureka/target/*.jar eureka.jar

RUN mv gateway/target/*.jar gateway.jar

RUN mv room/target/*.jar room.jar

RUN mv services/target/*.jar services.jar

RUN mv users/target/*.jar users.jar

RUN mv booking/target/*.jar booking.jar

EXPOSE 8081

EXPOSE 8761

ENV DB_HOST="db"

ENV DB_PORT="3306"

COPY --chmod=0777 run.sh .

ENTRYPOINT ["./run.sh"]