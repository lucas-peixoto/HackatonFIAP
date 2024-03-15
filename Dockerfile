FROM openjdk:21-slim

WORKDIR /app

COPY . .

COPY --chmod=0777 run.sh .

RUN apt-get update

RUN apt-get install -y maven

RUN mvn clean package -DskipTests=true -f eureka/pom.xml \
    && mvn clean package -DskipTests=true -f gateway/pom.xml \
    && mvn clean package -DskipTests=true -f room/pom.xml \
    && mvn clean package -DskipTests=true -f services/pom.xml \
    && mvn clean package -DskipTests=true -f users/pom.xml \
    && mvn clean package -DskipTests=true -f booking/pom.xml

RUN mv -rf eureka/target/*.jar eureka.jar

RUN mv -rf gateway/target/*.jar gateway.jar

RUN mv -rf room/target/*.jar room.jar

RUN mv -rf services/target/*.jar services.jar

RUN mv -rf users/target/*.jar users.jar

RUN mv -rf booking/target/*.jar booking.jar

EXPOSE 8081

ENTRYPOINT ["./run.sh"]