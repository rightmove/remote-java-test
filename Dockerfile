FROM openjdk:11
ADD target/*.jar myapplication
ENTRYPOINT ["java", "-jar","myapplication"]
EXPOSE 8080