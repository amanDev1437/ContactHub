FROM openjdk:22-jdk
ADD target/contact-hub.jar contact-hub.jar
ENTRYPOINT ["java", "-jar","/contact-hub.jar"]