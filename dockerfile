FROM openjdk:17
COPY target/Servicio-CineTickets-0.0.1-SNAPSHOT.jar servicio_cinetickets.jar
ENTRYPOINT ["java","-jar","servicio_cinetickets.jar"]