# Usa a imagem base oficial do Tomcat com JDK 17
FROM tomcat:10.1.0-jdk17

# Remove apps padrão para evitar conflitos
RUN rm -rf /usr/local/tomcat/webapps/*

# Define o diretório de trabalho
WORKDIR /usr/local/tomcat/webapps/

# Copia o WAR da aplicação
COPY ./target/LinkBio/link.bio.war ./link.bio.war

# Expõe a porta padrão do Tomcat
EXPOSE 8080

# Inicia o Tomcat
CMD ["catalina.sh", "run"]

# Expõe a porta padrão do Tomcat
EXPOSE 8080

# Variáveis de desempenho (pode ser sobrescrito no docker-compose)
ENV CATALINA_OPTS="-Xms512m -Xmx1024m \
  -Dserver.tomcat.connection-timeout=20000 \
  -Dserver.tomcat.max-http-header-size=1048576 \
  -Dserver.tomcat.max-swallow-size=20971520 \
  -Dserver.tomcat.keep-alive-timeout=20000 \
  -Dserver.tomcat.threads.max=200"

# Inicia o Tomcat
CMD ["catalina.sh", "run"]
