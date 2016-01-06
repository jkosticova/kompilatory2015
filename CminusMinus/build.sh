#!/bin/bash
java -jar antlr-4.5.1-complete.jar -visitor CminusMinus.g4
javac *java
gcc -shared -fPIC -std=c99 library.c -o library.so
jar xf antlr-4.5.1-complete.jar
jar cmf MANIFEST.MF cmm.jar -C ./ .
