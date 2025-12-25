FROM tomcat:11.0-jdk17

ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar \
    /usr/local/tomcat/lib/

COPY docker/tomcat/context.xml /usr/local/tomcat/conf/context.xml

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/web1.war /usr/local/tomcat/webapps/web1.war

EXPOSE 8080

