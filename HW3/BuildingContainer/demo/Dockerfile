FROM adoptopenjdk/openjdk11
EXPOSE 3001
ADD target/demo-0.0.1-SNAPSHOT.jar firstapp.jar
ENTRYPOINT ["java","-jar","/firstapp.jar"]
CMD ["--outerServer=secondapp"]