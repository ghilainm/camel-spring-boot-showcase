#!/usr/bin/env bash

docker-compose up "jaeger" "${PROTOTYPE_BUILD_INFRA_SCRIPTS}/jaeger" "false" "false" "false"
