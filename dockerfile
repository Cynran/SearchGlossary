FROM maven:3.5.4-jdk-11 as maven

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

FROM tomcat:9.0-jdk11-openjdk-slim

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY --from=maven /tmp/target/search-glossary-*.war $CATALINA_HOME/webapps/ROOT.war


