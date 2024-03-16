#!/bin/sh

echo "Starting the application"
java -jar /app/eureka.jar &
java -jar /app/gateway.jar &
java -jar /app/room.jar &
java -jar /app/services.jar &
java -jar /app/users.jar &
java -jar /app/booking.jar