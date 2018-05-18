#!/usr/bin/env bash

env=$1

if [[ "$env" = "prod" ]]; then
    echo rolling back changes on prod!!
    ssh centos@172.31.29.244 'docker service rollback charlacd_app'
    ssh centos@172.31.29.244 'docker service rollback charlacd_web'
    ssh centos@172.31.29.244 'docker service rollback charlacd_web_nossl'
elif [[ "$env" = "dev" ]]; then
    echo rolling back services on local server
    docker service rollback charlacd_app
    docker service rollback charlacd_web_nossl
    docker service rollback charlacd_db
else
    echo "I don't know your environment"
    exit 1
fi