FROM adoptopenjdk/openjdk12:jre-12.0.2_10-ubuntu

WORKDIR /var/www/app

COPY /build/libs/address-book-ms-0.0.1-SNAPSHOT.jar /var/www/app/address-service.jar
COPY /scripts/start.sh /var/www/app/
EXPOSE 9080
ENTRYPOINT ["sh","/var/www/app/start.sh"]
