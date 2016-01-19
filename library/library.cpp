#include <stdlib.h>
#include <stdio.h>

extern "C" {
	int printInt(int a) {
		printf("%d", a);
		return 0;
	}

	int printChar(char a) {
        printf("%c", a);
        return 0;
    }

    int printString(char *a) {
        printf("%s", a);
        return 0;
    }

    int printBool(bool a) {
        if(a) printf("true");
        else printf("false");
        return 0;
    }

    int scanInt() {
        int a;
        scanf("%d", &a);
        return a;
    }

    char scanChar() {
            char a;
            scanf("%c", &a);
            return a;
    }

    int printEndl() {
        printf("\n");
        return 0;
    }

	int iexp(int a, int b) {
		int ret = 1;
		for (int i = 0; i < b; i++) {
		        ret *= a;
		}
		return ret;
	}
}
