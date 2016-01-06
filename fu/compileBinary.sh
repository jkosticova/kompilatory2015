#!/bin/bash
java -jar out/fu.jar < $1 > $1.ll
llc $1.ll
gcc -o $2 $1.s
rm -rf $1.ll
rm -rf $1.s
