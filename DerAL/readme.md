Verzie pouzitych nastrojov:
ANTLR 4
ST 4.0.8
JAVA 8 - java version "1.8.0_65"
	 Java(TM) SE Runtime Environment (build 1.8.0_65-b17)
	 Java HotSpot(TM) 64-Bit Server VM (build 25.65-b01, mixed mode)
Linux Mint 17.1 64-bit
llvm - pravdepodobne 3.4, ale nikde som nenasiel ako to urcit nejak spolahlivo... mam dispozicii prikazy tak ze lli, lli-3.4,llvm-ar,llvm-ar-3.4, atd..)

Zmeny oproti špecifikácii:
- klúčové slovo vrat som zmeni na vrac (lebo spisovne po požitavsky je rozkazovací spôsob od vrátiť vrác).
- floaty su neni 32-bitové ale 64. Teda vlaste sú to double
- kvôli počítaniu čísel riadkov v chzbových hláškach nemôže byť blokový komentár hocikde, ale iba "na úrovni príkazov", t.j. sa nedá do stredu príkazu vložiť blokový komentár
- vynechal som typ prirodzene (t.j. unsigned)
- typ nic som zmenil na nist (tiež jazykové dôvody, spisovne je ništ)
- bonusové veci som nerobil
- vstup a výstup som nakoniec poriešil built-in funkciami a to konkrétne (aby to dodalo atmosféru a vyhli sme sa angličtine):
	znak vezni();
	nist tiskaj(znak z);
	nist vezniRetazec(retazec r);
	nist tiskajRetazec(retazec r);
	cele vezniCele();
	nist tiskajCele(cele c);
	realne vezniRealne();
	nist tiskajRealne(realne r);
- možno ešte nejaký drobný syntaktický cukor som trocha pozmenil oproti tomu čo bolo v špecke, ale nič ďalšie zásadné by to byť nemalo. Však v gramatike vidno tú syntax asi najlepšie.
