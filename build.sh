mvn clean package -DskipTests=true -f eureka/pom.xml
mvn clean package -DskipTests=true -f gateway/pom.xml
mvn clean package -DskipTests=true -f room/pom.xml
mvn clean package -DskipTests=true -f services/pom.xml
mvn clean package -DskipTests=true -f user/pom.xml
mvn clean package -DskipTests=true -f booking/pom.xml