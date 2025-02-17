# Użyj obrazu Eclipse Temurin (JDK 17)
FROM eclipse-temurin:17-jdk

# Zmienna środowiskowa dla portu
ENV SERVER_PORT=8082

# Określenie katalogu roboczego w kontenerze
WORKDIR /app

# Kopiowanie pliku .jar do katalogu roboczego kontenera
COPY target/character-management-service-0.0.1-SNAPSHOT.jar app.jar

# Otwórz port, na którym aplikacja będzie nasłuchiwać
EXPOSE 8082

# Uruchom aplikację Spring Boot w kontenerze
ENTRYPOINT ["java", "-jar", "app.jar"]