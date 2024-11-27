FROM openjdk:17-jdk-slim

# Establece el director

WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY target/bibliotecaIUD-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]