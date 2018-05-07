FROM ubuntu:latest
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk wget maven
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ADD ./ ./project/
EXPOSE 8080
RUN cd /project && mvn package -DskipTests
CMD java -jar ./target/Agents_e2b-0.0.1.jar