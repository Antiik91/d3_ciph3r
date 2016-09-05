# Mitä on testattu
Ohjelmassa on testattu Käyttöliittymän ja logiikan toimivuutta,
sekä käsin että JUnit testeillä, missä se on suotavaa.
	
Caesar Cipherissa testataan erilaisilla muunnoksilla salauksen ja sen purkamisen toimivuutta erilaisissa merkkijonoissa, eri siirtymiä käyttämällä. Lisäksi pyritään eliminoimaan käyttäjän vahingossa syöttämät negatiiviset siirtymät muuntamalla ne takaisin positiivisiksi.
	
DESin kanssa testataan, että avaimen puuttuminen ei kaada ohjelmaa, vaan silloin luodaan uusi avain, jota on käytetty salauksessa tai salauksen purkamisessa. Lisäksi testataan avaingeneraattorin luomia avaimia, sillä on erittäin tärkeää että ne ovat 8 kirjaimisia. Lisäksi testataan syötteiden salaamisen ja purkamisen toimivuutta. DESIn privaattimetodeja, kuten permutaation metodia ei ole JUnitilla testattu, mutta käsin sitäkin enemmän. Lisäksi, koska DES käyttää vahvasti getbit ja setbit metodeja, niin privaattimetodien toimivuus varmistetaan BitTools metodeilla ja niiden JUnit testeillä. 
	
Aputyökaluluokan testeissä on testattu metodien toimivuutta JUnit testeillä, ja niiden rikkoutumisen huomaa nopeasti DES salauksen ja tai purun epäonnistuessa. Käytännön testaus toimivuudesta tulee joka kerran kun teksti salataan / salaus puretaan.
	
Tiedostonkäsittelyluokkia on testattu vain käsin, sillä tiedostonhallinan testaaminen ei kuulu kurssiin. Mutta koska ne ovat oleellinen osa ohjelman toimivuutta, niitä on testattu useita kertoja ja  siten varmistettu luokkien toimivuus.

Logiikka luokassa on testtu logiikan kannalta olennaisia metodeja JUnit testeillä. Koska DES ja Caesarin toimivuudesta oli jo omat testit, jätin niiden testaamisen uudestaan logiikan kautta pois, sillä luokkien omat testit takaavat niiden toimivuuden.
	
# Syötteet
Syötteinä on käytetty eripituisia merkkijonoja sekä tekstitiedostoja. Caesar Cipherissa ei toimi ääkköset joten olen siinä käyttänyt englantia testauksissa. Suurimmat testatut tekstitiedostot olivat DES algoritmille oli Edgar allan poen [The works of Edgar allan poe](https://github.com/Antiik91/d3_ciph3r/blob/master/dokumentaatio/THE%20WORKS%20OF%20EDGAR%20ALLAN%20POE.txt) (567kt  plaintext)  ja Caesar algoritmille [Ensimmäinen osa kyseisestä teoksesta.](https://github.com/Antiik91/d3_ciph3r/blob/master/dokumentaatio/THE%20PURLOINED%20LETTER%20BY%20EDGAR%20ALLAN%20POE.txt) (42kt plaintext). [Teos on otettu Gutenberg projektista.](https://www.gutenberg.org/wiki/Main_Page)

# Toistettavuus
  JUnit testit voidaan toistaa netbeansissa suorittamalla testit esimerkiksi alt + f6 pikanäppäimellä. Suorituskykytestauksen voit toistaa seuraavasti:
  * Main Luokassa
  Ensiksi kommentoi pois seuraavat rivit:

	>  UserInterface ui = new UserInterface(new Scanner(System.in));
	
	> ui.run();
	
 Seuraavaksi kopioi seuraavat rivit main luokkaan: 
 	> Logic loc = new Logic();
 	
 	>  String pap = loc.returnFileAsString(/* PATH GOES HERE */);
 	
 	* HUOM! Muista käyttää oikeaa polkua ylläolevassa koodirivissä jotta saat tekstitieodostosi käyttöön.
	

	> CaesarCipher cs = new CaesarCipher(); 

	> DES des = new DES();
	
	Halutessasi käyttää DES metodeja saat ne seuraavilla komennoilla:
	
	> byte[] encrypt = des.encryptPlaintext(pap, key);
	
	>  byte[] decrypt = des.decryptData(encrypt, key);
	
	Halutessasi käyttää Caesar metodeja saat ne seuraavilla komennoilla: 
	
	> String test1 = cs.encrypt(pap, 3);
	
	> cs.decrypt(test1, 3);

# Empiirinen testaus

* DES

Käytin Suorituskykytestauksessa DES:lle The Works of Edgar Allan Poen kokoelmaa, jonka kokonaiskoko on 567 kilotavua plaintekstinä.Suorituksia tein kymmenen kappaletta saadakseni hajontaa. Suoritukset Encryptaukselle ja Decryptaukselle olivat seuraavanlaiset (ajat millisekunteina) :

| Salaus        | Salauksen purku           |
| ------------- |-------------|
| 802          | 752    |
| 725          | 768    |
| 805          | 731    |
| 857          | 738    |
| 841          | 759    |
| 830          | 753    |
| 810          | 780    |
|  732         | 749    |
| 801          | 743    |
| 811          | 736    |
| Keskiarvo        | Keskiarvo           |
| 801,4| 748,1 |
| Keskiarvo / kt       | Keskiarvo   / kt         |
| 1.41         | 1.31           |
Salauksen ja purun välillä on selvä, noin 53 millisekunnin ero. Eron selittänee osittain se, että salausta kutsuttaessa sille lähetetään parametrinä string teksti, joka ensiksi käännetään tavukooditaulukoksi ennen salauksen aloittamista. Salauksen purkaminen alkaa taas suoraan tavukooditaulukon purkamisella. Vahvistukseni epäilylle koodin suoritusaika on suoraan riippuvainen salattavan tiedoston, eli tekstin koosta. Näissä suorituskykytestauksissa ei suoritettu salatun tavukoodin kirjoittamista tiedostoon, sillä koko Edgar allan Poen tuotannon salatun version kirjoittamisessa kuluva aika ei ole mielekästä testattavaa, eikä testauksen kannalta oleellista, sillä emme halunneet tietää levykirjoituksen nopeutta. 
Alogritmin aikavaativuus on O(n) missä n on viestin pituus 64 bittisinä lohkoina.

* Caesar Cipher

Caesar Cipherin tapauksessa jouduin pienentämään testattavan tiedoston kokoa, sillä koko Works OF edgar Allan Poen kääntäminen kirjain kirjaimelta vie aivan liian paljon aikaa. Suoritusykytestauksessa käytin ensimmäistä osaa alkuperäisestä tiedostosta. Ja tämän koko on 42kt plaintekstinä. Suoritukset olivat seuraavanlaiset (ajat millisekunteina): 

| Salaus        | Salauksen purku           |
| ------------- |-------------|
| 1241          | 1071    |
| 1223          | 1071    |
| 1169          | 993    |
| 1199          | 1065    |
| 1212          | 1075    |
| 1212          | 1084    |
| 1232          | 1074    |
|  1240         | 1071    |
| 1230          | 1070    |
| 1237          | 1070    |
| Keskiarvo        | Keskiarvo           |
| 1095.5| 1070| 
| Keskiarvo/ kt        | Keskiarvo / kt          |
| 26.07        | 25.48           |
Suoraan nähdään että että DES ja Caesar eivät ole sinällään vertailukelpoiset, siinä missä DES toimii tavu ja bittioperaatioilla, toimii
Caesar Cipher kirjain kirjaimelta, joka on tietokoneelle paljon raskaampi operaatio. 
Algoritmin aikavaativuus on O(n) myös, mutta tässä n viittaa kirjainten määrään. 
