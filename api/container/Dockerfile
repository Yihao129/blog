FROM tomcat:9.0.16-jre8

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/ROOT.war
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY ./*.war /usr/local/tomcat/webapps/ROOT.war

COPY ./setenv.sh /usr/local/tomcat/bin/setenv.sh


