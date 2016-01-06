# Friendly & Unpredictable programming language (FU)

## Verzie použitého software:

#### Linux

Kód používa externú funkciu `i8* malloc(i64)`, čiže je korektne skompilovatelny a spustiteľný iba na 64-bitovom linuxe.

####LLVM

Otestované s:

    LLVM (http://llvm.org/):
      LLVM version 3.7.0
      Optimized build.
      Built Sep 20 2015 (22:09:32).
      Default target: x86_64-redhat-linux-gnu
      Host CPU: ivybridge

####Java

Otestované:

    openjdk version "1.8.0_65"
    OpenJDK Runtime Environment (build 1.8.0_65-b17)
    OpenJDK 64-Bit Server VM (build 25.65-b01, mixed mode)

Mala by stačiť akákoľvek verzia Javy 8. Nižšie verzie Javy nie sú podporované (používam lambda funkcie, String.join a ďalšie "novinky")

####ANTLR & StringTemplate
    antlr-4.5.1-complete.jar

##Špecifikácia:

Dostupná samostatne v súbore `spec.md` resp `spec.html`

