# Alpine Linux with OpenJDK JRE

FROM java:8
COPY target/*.jar ./service.jar
ENTRYPOINT exec java $JAVA_OPTS -jar /service.jar
