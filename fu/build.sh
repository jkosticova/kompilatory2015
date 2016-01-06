#!/bin/bash

rm -rf out/
rm -rf gen/
rm -rf build/
mkdir gen
mkdir out
mkdir build
java -jar src/antlr-4.5.1-complete.jar -visitor -no-listener -o gen src/fu.g4
javac -cp src/antlr-4.5.1-complete.jar gen/src/*.java src/*.java -d build
cd build
jar xf ../src/antlr-4.5.1-complete.jar
cd ..
jar cmf src/META-INF/MANIFEST.MF out/fu.jar -C build .
rm -rf gen/
rm -rf build/
