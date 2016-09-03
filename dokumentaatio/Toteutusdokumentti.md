# Ohjelman yleisrakenne
Ohjelman käynnistyessä käyttäjälle esitellään päävalikko, josta käyttäjä valitsee haluamansa toiminnan.
Salauksen luonti ja purkuvaihtoehtoja on kaksi. Caesar Cipher ja DES. 
Valitessasi salausmenetelmän, esitellään vaihtoehdoksi joko
kirjoittaa halutun tekstin tai salata / purkaa halutun tekstitiedoston.
Ohjelman Logic luokka pyörittää kokonaisuutta, mitä tietoja se saa UIlta.
	
	
# Saavutetut aika-ja tilavaativuudet
tavoitteksi asetetut aikavaativuudet saatiin tavoitettua. DES aikavaativuus on O(n) missä n on salattavien lohkojen määrä ja Caesar Cipherissa O(n), missä n viittaa kirjainten lukumäärään. Kyseiset algoritmit eivät ole millään tavalla vertailukelpoisia keskenään nopeudessa, sillä toinen hoitaa tietokoneelle tyypillisiä tavu ja bittitason operaatioita ja toinen käy kirjain kerrallaan tekstiä läpi. aikavaativuudessa ei oteta huomioon viestien kirjoittamista ja tallentamista levylle, mitkä vievät jo huomattavasti enenmmän aikaa. Aikavaativuuksien tarkempi analyysi löytyy [Testausdokumentaatiosta](https://github.com/Antiik91/d3_ciph3r/blob/master/dokumentaatio/Testausdokumentti.md)
	
# Puutteet ja parannusehdotukset.
* DES Algoritmistä jäi puuttumaan Substitution metodi, joka käytännössä vahvistaa algoritmin salausta huomattavasti. Sen 	 toteuttaminen oli itselleni ylivoimaisen haastavaa, enkä näihin aikamääreisiin pystynyt sitä toteuttamaan. 
* Ääkköset toimivat satunnaisilla koneilla, joissain ei toimi ollenkaan, joissain toimii moitteetta DESn kanssa. caesar Cipherin kanssa ääkköset eivät toimi. 
* Kaksi DES testiä epäonnistuu linux käyttöjärjestelmillä, kyseessä ovat erikoismerkkien salaus ja purku testaukset.

# Lähteet
http://page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm

https://fi.wikipedia.org/wiki/DES
	
