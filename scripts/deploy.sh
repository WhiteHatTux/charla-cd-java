#!/usr/bin/env bash

env=$1
action=$2

if [[ ! $env =~ ^(dev|prod)$ ]]; then
    echo env \"$1\" is not available please choose from \"dev, prod\" and pass as argument
    exit 1
fi

if [[ $action =~ ^(deploy)$ ]]; then
    echo will \"$action\" service
else
    echo will only \"prepare\" service
fi


# prepare docker-compose with new version tags from build/version.txt
mkdir -p build/to-deploy
cp docker-compose.template.yml build/to-deploy/docker-compose.yml
cp docker-compose-dev.yml build/to-deploy/docker-compose-dev.yml
cp docker-compose-prod.yml build/to-deploy/docker-compose-prod.yml
cp env-files/$1 build/to-deploy/env

pushd build/to-deploy
. ./env
sed -i "s/REPLACE_DB_HOST/$REPLACE_DB_HOST/g" docker-compose.yml
sed -i "s/REPLACE_DB_PASSWORD/$REPLACE_DB_PASSWORD/g" docker-compose.yml
sed -i "s/REPLACE_APP_IMAGE/$(cat ../version.txt)/g" docker-compose.yml
sed -i "s/REPLACE_APP_IMAGE/$(cat ../version.txt)/g" docker-compose-prod.yml

echo $action
if [[ ! "$action" = "deploy" ]]; then
    echo exiting, because only prepare was requested
    pushd
    exit 0
fi
# start/upgrade the whole thing
if [[ "$env" = "prod" ]] ; then
    prod=172.31.29.244
    # copy docker-compose to prod
    scp -o StrictHostKeyChecking=no docker-compose.yml docker-compose-prod.yml centos@"$prod":/home/centos/deploy/
    # login to aws registry on prod server
    ssh -o StrictHostKeyChecking=no centos@"$prod" '$(aws ecr get-login --no-include-email)'
    # run on prod server
    ssh -o StrictHostKeyChecking=no centos@"$prod" "docker stack deploy --compose-file=/home/centos/deploy/docker-compose.yml --compose-file=/home/centos/deploy/docker-compose-prod.yml charlacd"
elif [[ "$env" = "dev" ]]; then
    pushd ../../nginx-build
        docker build -t nginx-charla:latest .
    popd
    docker stack deploy --compose-file=docker-compose.yml --compose-file=docker-compose-dev.yml charlacd
fi

popd