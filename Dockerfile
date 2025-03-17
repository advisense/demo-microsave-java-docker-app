FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10


LABEL BASE_IMAGE="registry.access.redhat.com/ubi8/ubi-minimal:8.10"
LABEL JAVA_VERSION="23"


RUN microdnf install --nodocs java-21-openjdk-headless && microdnf clean all

WORKDIR /work/
COPY target/*.jar /work/application.jar

EXPOSE 8080
CMD ["java", "-jar","application.jar"]