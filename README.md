Public Transport Monitor [![Build Status](https://travis-ci.org/lukaszbarc/wptm.svg?branch=master)](https://travis-ci.org/lukaszbarc/wptm)
========================

Build
-----
Simply:
> mvn clean install

API DOC:
--------

Run app and then go to:

> http://localhost:8080/swagger-ui.html

Run mocked Warsaw Trams Api to work offline
-------------------------------------------
> java -jar apps\mock-warsaw-trams-app\target\mock-warsaw-trams-app-0.0.1-SNAPSHOT.jar

Server starts on localhsot:8099 and exposes two resources:

> http://localhost:8099/trams

> http://localhost:8099/withPossibleEmptyResult?emptyResultEveryN=50

Run data store app connected to mocked api
------------------------------------------
> java -jar apps\data-store-app\target\data-store-app-0.0.1-SNAPSHOT.jar --spring.profiles.active=mock-api

Server starts on localhsot:8080