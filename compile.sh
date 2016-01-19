# !/usr/bin/bash

echo "Kompilujem $1 do $2"

java -classpath bin:$CLASSPATH Exmi $1 > $1.ll && \
opt -S -std-link-opts $1.ll > $1.optimized.ll && \
llc $1.optimized.ll -o $1.s  && \
g++ library/library.o $1.s -o $2

rm $1.s
rm $1.ll
rm $1.optimized.ll
