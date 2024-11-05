# Base image được sử dụng để build image
FROM --platform=amd64 openjdk:17.0.2-oraclelinux8

# Thông tin tác giả
LABEL authors="buihien"

# Set working directory trong container
WORKDIR /app

# Copy file .env vào container tại thư mục /app
COPY .env /app/.env

# Copy file JAR được build từ ứng dụng Spring Boot vào working directory trong container
COPY target/backend-assignment3-0.0.1-SNAPSHOT.jar app.jar

# Expose port của ứng dụng
EXPOSE 8080

# Nạp file .env và chạy ứng dụng Spring Boot
CMD ["/bin/sh", "-c", "export $(grep -v '^#' /app/.env | xargs) && java -jar app.jar"]