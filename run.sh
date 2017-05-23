#!/bin/bash

# Initialise directories
mkdir -p bin/

# Compile
javac -d "bin/" -classpath "libs/*" $(find InteractionDesign | grep .java)

# Copy over resources
cp -r resources/* bin/

# Run
java -cp "bin/:bin/libs/*" com.github.hnefatl.interactiondesign.InteractionDesign
