#Špecifikácia

##Hlavné zmeny oproti pôvodnej špecifikácií

 - Mena premenných môžu byť už len alfanumerické
 - Dospecifikovane veci podľa pokynov
 - Upravená forma špecifikácie (Markdown)
 - Uzrejmene pravidlá o písaní bodkočiarok

##Premenné

###Základné typy premenných

 - `\/` - 32-bitové signed číslo (kompatibilné s `int32_t`)
 - \\ - null-terminated string (kompatibilné s `uint8_t*`)

###Typ pole

 - `\/[]` - pole 32-bitových čísel (`int32_t*`)
 - `\\[]` - pole string-ov (`int8_t**`)
 - Viacrozmerné polia sú implementované ako polia poli (napr. `int32_t***`)
 - jagged arrays alebo "lineárne" viacrozmerné polia (`int32_t[][][]`) nie sú podporované



###Práca s premennými

 - `\/ x`; (vytvorenie bez inicializácie)
 - `\/ y = 47;` (vytvorenie s inicializáciou)
 - `x = 42;` (zmena hodnoty)
 - `x = y;` (kopírovanie hodnoty premennej `y` do `x`)
 - `\\ ahoj = "cau";`
 - `\\ wau = ahoj + "svet";`
 - Premenné sú po vytvorení inicializované na `0`, resp. `""`;
 - Reťazce nie sú meniteľné po znakoch
 - Reťazce sú spajatelne pomocou `+`.
 - Reťazce (bohužiaľ) nemôžu obsahovať whitespace :(
 - Mena premenných musia začínať písmenom a môžu obsahovať písmena a čísla
 - Premenné sú alokované na stack-u (`alloca`)
 - Reťazce sú immutable (`+` aj načítavanie vytvárajú nový reťazec)

###Operácie s premennými a hodnotami

- Reťazcové operácie na reťazcoch:
  - Binárne: +
- Číselné operácie na číslach:
  - Unárne: +, -
  - Binárne: +, -, *, /, %
- Číselné operácie na premenných:
  - ++, --, +=, -=, *=, /=, %=
- Logické operácie na číslach: (vracajú 0 alebo 1)
  - Unárne: !
  - Binárne: &&, ||, >, <, <=, >=, <>, ==

##Statements

###If
`?(podmienka):{ then }::{ else }`

 - bloky pre časti then a else sú povinné, môžu byť prázdne 
    (napr. `?(podmienka):{}::{};`)
 - podmienka sa vyhodnocuje logicky podľa týchto pravidiel:
 - čísla majú hodnotu PRAVDA ak nie sú rovné 0
 - reťazce majú hodnotu PRAVDA ak sú neprázdne
 - polia majú vždy hodnotu PRAVDA

###For
`@(statement; expression; statement) { then } `

 - syntax podobná ako v C
 - blokové zátvorky sú povinné
 - neexistuje block-scoping.. (tj. ak sa použije štýlom `@(\/i=0;i<10;i++){}` tak `i` ostane zadefinovaná aj po skončení cyklu)
 - podmienka sa vyhodnocuje rovnako ako v if-e

###While
`$(expression){ then }`

 - syntax podobná ako v C
 - podmienka sa vyhodnocuje rovnako ako v if-e

##Funkcie

###Typy funkcií

 - `\/`, `\\` - vrátia `\/`, `\\` respectively
 - `\-` - nevráti nič (void)

###Práca s funkciami

 - Premenné typu int, sú copy-by-value, polia sú copy-by-reference
 - Premenné typu string sú copy-by-reference, ale jazyk FU neumožňuje ich zmenu po znakoch. Zmena stringu po znakoch je umoznitelna pomocou použitia externej funkcie napísaná v jazyku C alebo kompatibilnom.
 - Na vrátenie poľa je odporúčané využiť vlastnosť copy-by-reference :)
 - Funkcie si svoje premenné alokujú na stack-u s nasledovnými výnimkami:
 - Ak funkcia vracia string, je predtým jeho hodnota skopírovaná na heap
 - Ak funkcia vkladá string do poľa, ktoré dostala ako argument, je pred priradením jeho hodnota skopírovaná na heap.
 - Funkcie sú kompatibilné s C
   ( vo FU sa dá napísať knižnica použiteľná v jazyku C a opačne )
 - Definícia funkcie:
   `\/ menoFunkcie(\/ argument1, \/[] argument2) { return argument1 + argument2[0] + 47; };`
 - Deklarácia externej funkcie:
   `\/ externaFunkcia(\/, \\);`
 - Volanie funkcie:
   `\/[2] pole;`
   `\/ x = menoFunkcie(42, pole);`
 - Externé funkcie sa volajú rovnako ako interné
 - Funkcie je potrebné definovať pred použitím a dodržať počet a typy argumentov pri volaní
 - Rekurzívne funkcie sú povolené
 - Forward declaration v jazyku nie je povolená, treba použiť viacero súborov
 - Nie je možné mať dve funkcie s rovnakým názvom a iným počtom argumentov.
 - Nie je možné predefinovať knižničné funkcie, tj. nasledovné:
  - Vstavané knižničné funkcie (interné a externé)
   - `\/ strlen(\\ x)`
   - `\- printSpace()`
   - `\- printNewLine()`
   - `\- printLnInt(\/ c)`
   - `\- printInt(\/ c)`
   - `\- printLnStr(\/ c)`
   - `\- printStr(\/ c)`
   - `\\ strcat(\\ x, \\ y)`
  -  Externé funkcie používané, ktoré nie sú kompatibilné s FU jazykom:
   - `i32 @scanf(i8*, ...)`
   - `i32 @printf(i8*, ...)`
   - `i8* @malloc(i64)`
   - `i8* @strcpy(i8*, i8*) `
 
##Input/Output
 - Načítavanie do premennej `\/` alebo `\\`:
  -   `\/ x; x<<;`
   - Načítavanie do premennej \\ načítava min(1023 znakov, počet znakov po prvý whitespace) (`scanf("%1023s")`)
 - Načítavanie do pola:
  - `\/[n] x; x<<\/[n]; `
   - Pri načítavaní do poľa treba uviesť typ a počet prvkov
 - Vypisovanie hodnoty typu `\/` alebo `\\`:
   - `(47 + 24)>>; (47 + 24)>>>;`
   - tri `>` ukončia výstup novým riadkom
   - dve `>` iba výpišu hodnotu
 - Vypisovanie polí:
   - `\/[n] x; x>>>\/[5]; x>>\/[5];`
   - tri `>` oddeľujú prvky poľa novým riadkom
   - dve `>` oddeľujú prvky poľa medzerou
   - treba špecifikovať správny typ poľa a počet prvkov <= veľkosti poľa

##Komentáre
Jazyk podporuje riadkové komentáre začínajúce a končiace znakom `~` (tilde)

##Sémantické pravidlá
- Jazyk je typový a (mal by) kontrolovať typy všade kde je to možné
- Premenné sú function-scoped. Premenné definované vnútri blokov for, if, 
   while sú použiteľné aj mimo nich. 
- Premenné nemôžu byť predefinované (`\\x;\/x;~ani znovu~\\x;`)
- Whitespace sú ignorované kompilátorom 
- Samostatné existujúci (bez if/for/while) blok príkazov nejde vytvoriť 
   (nemá zmysel keďže jazyk má function-scoped premenné).
- Príkazy treba ukončovať bodkočiarkou pokiaľ nie je povedané ináč
   ( napr. posledný príkaz vo for-e )
  - Toto sa týka aj blokových príkazov (if, for, while) a definície a 
     deklarácie funkcii

##Kľúčové slova
Jazyk nemá žiadne alfanumerické kľúčové slova

