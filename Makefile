default: bin/Exmi.class

src/ExmiVisitor.java: Exmi.g4
	antlr4 -visitor Exmi.g4 -o ./src

library/library.so: library/library.cpp
	g++ -shared -fPIC library/library.cpp -o library/library.so

library/library.o: library/library.cpp
	g++ -fPIC library/library.cpp -c -o library/library.o

bin/Exmi.class: src/*.java library/library.so library/library.o
	mkdir -p bin
	javac src/*.java -d bin/
