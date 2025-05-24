# Сборка приложения
FROM eclipse-temurin:17-jdk as build
WORKDIR /app

# Копируем только то, что нужно для сборки
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Собираем приложение
RUN ./mvnw clean package -DskipTests

# Финальный образ
FROM eclipse-temurin:17-jre
WORKDIR /app

# Копируем собранный JAR
COPY --from=build /app/target/*.jar app.jar

# Настройки
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]