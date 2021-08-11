#!/usr/bin/env sh

# Start jaeger
cd jaeger
./start-jaeger.sh &
cd ..

# Start spring boot app
./gradlew bootRun
