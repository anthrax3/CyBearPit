#!/bin/bash

# Make a new docker container each time the tests are run
printf '%s\n' " => Starting WildFly-Arquillian container from"
NOWDIR=$(pwd)
cd ../docker/wildfly/arqtest/ || exit
printf '%s\n' "        $(pwd)"
./runServer.sh new
cd "$NOWDIR" || exit

# Run the tests
mvn clean test -Parq-wildfly-remote

# Kill the container
printf '%s\n' " => Stopping WildFly-Arquillian container"
docker stop judgebean-arqtest-wildfly
docker rm judgebean-arqtest-wildfly
