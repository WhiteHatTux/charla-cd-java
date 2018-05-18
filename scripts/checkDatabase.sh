#!/usr/bin/env bash

pwd


docker-compose up -d

count=0
healthSuccess=0
while [ $count -le 5 ]
do
  count=$(( $count +1 ))
  sleep 5
  curl -o health 'http://localhost:8080/actuator/health'
  cat health | grep "Hello World"
  healthAPI=`echo $?`
  if [ $healthAPI -eq 0 ]; then
      healthSuccess=1
      break
  fi
done

if [ $healthSuccess -eq 1 ]; then
  echo "server started without error against database from repository"
else
  echo "could not get success on master health - breaking build. container log follows"
  docker-compose -f docker-compose-dev.yml logs app
  docker-compose -f docker-compose-dev.yml down -v
  exit 1
fi
docker-compose -f docker-compose-dev.yml down -v