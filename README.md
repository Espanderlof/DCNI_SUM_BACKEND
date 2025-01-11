# DCNI_SUM_BACKEND
DESARROLLO CLOUD NATIVE I - SUMATIVA

# Ejecutar app Spring Boot
mvn spring-boot:run

# Levantar contenedor Docker
docker build -t alertas_medicas_backend .
docker run --name alertas_medicas_backend -p 8081:8081 alertas_medicas_backend