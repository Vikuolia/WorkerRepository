FROM openjdk:11-jre
WORKDIR D: /3 курс/ТРСПО/lab3/worker/out/artifacts/worker_jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app.jar"]