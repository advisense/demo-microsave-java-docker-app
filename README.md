# demo-microsave-java-docker-app
demo / examples repo used for learning purposes



a very simple Hello World java application with Spring Boot and Maven, containerized with dockerfile, ready to build and deployed with a very simple way.
contains some dependencies.

## prerequisite

- install JDK
  - add JAVA_HOME 
  - add to path
- install maven and add to path
- install docker desktop

### Note on java version

Java version is referanced both in the pom.xml and Dockerfile
These needs to match and correspond to your code.


## How to Build
```
docker build -t hello-world-java-docker .
```  

## How to Run
```
docker run -it hello-world-java-docker
```

## Referances

based on this example: https://github.com/edwin/hello-world-java-docker
