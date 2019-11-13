#!/bin/bash
mvn clean package
cd docker || exit
docker-compose down -v
docker-compose up --build
cd .. || exit
