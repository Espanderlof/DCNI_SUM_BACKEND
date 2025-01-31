# DCNI_SUM_BACKEND
DESARROLLO CLOUD NATIVE I - SUMATIVA

# Ejecutar app Spring Boot
mvn spring-boot:run

# Levantar contenedor Docker
docker build -t alertas_medicas_backend .
docker run --name alertas_medicas_backend -p 8081:8081 alertas_medicas_backend

# DockerHub
1. Crear repo en https://hub.docker.com/
2. Primero, asegúrate de estar logueado en Docker Hub desde tu terminal
    docker login
3. Identifica tu imagen local. Puedes ver tus imágenes locales con:
    docker images
4. Etiqueta tu imagen local con el formato requerido por Docker Hub:
    Por ejemplo, si tu imagen local se llama "backend-app:1.0", los comandos serían:
    docker tag alertas_medicas_backend espanderlof/alertas_medicas:latest
    docker push espanderlof/alertas_medicas:latest

# Play with Docker
1. ir a https://labs.play-with-docker.com/
2. copiar repo de dockerHub
    docker pull espanderlof/alertas_medicas:latest
3. levantar contenedor
    docker run -d --network host --name alertas_medicas_backend espanderlof/alertas_medicas:latest
    Para Azure sudo docker run -d -p 8081:8081 --name alertas_medicas_backend espanderlof/alertas_medicas:latest
4. verificar contenedores
    docker ps

# volver a iniciar un contenedor ya creado en docker MV azure
1. traer todos los conenedores
    docker ps -a
1.1 en caso de actualizar el contenedor realizar un pull primero    
    docker pull espanderlof/alertas_medicas:latest
1.2 eliminar el contenedor actual
    docker rm alertas_medicas_backend
1.3 volver a levandar el contenedor
    sudo docker run -d -p 8081:8081 --name alertas_medicas_backend espanderlof/alertas_medicas:latest
2.  llamar al contenedor
    sudo docker start alertas_medicas_backend
3. revisar si se ejecuto el contenedor
    docker ps