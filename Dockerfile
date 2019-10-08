# Copyright 2018-2019 Florenz A. P. Hollebrandse. All rights reserved. 
FROM openjdk:8-jre-alpine3.9

COPY target/icalendar-validator-*-with-dependencies.jar /app/app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
