#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void printInt(int *a) {
	printf("%d\n", *a);
}

void printString(char *s) {
	puts(s);
}

void scanInt(int *a) {
	scanf("%d", a);
}

void scanString(char *s) {
	scanf("%s", s);
}
