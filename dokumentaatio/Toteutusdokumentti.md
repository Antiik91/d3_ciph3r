# Ohjelman yleisrakenne
Ohjelman käynnistyessä käyttäjälle esitellään päävalikko, josta käyttäjä valitsee haluamansa toiminnan.
Salauksen luonti ja purkuvaihtoehtoja on kaksi. Caesar Cipher ja DES. 
Valitessasi salausmenetelmän, esitellään vaihtoehdoksi joko
kirjoittaa haluttu tekstin tai salata / purkaa haluttu tekstitiedosto.
Ohjelman Logic luokka pyörittää kokonaisuutta, ja saa käskyjä tietoja UIlta.
	
	
# Saavutetut aika-ja tilavaativuudet
tavoitteksi asetetut aikavaativuudet saatiin tavoitettua. DES aikavaativuus on O(n) missä n on salattavien lohkojen määrä ja Caesar Cipherissa O(n), missä n viittaa kirjainten lukumäärään. Kyseiset algoritmit eivät ole millään tavalla vertailukelpoisia keskenään nopeudessa, sillä toinen hoitaa tietokoneelle tyypillisiä tavu ja bittitason operaatioita ja toinen käy kirjain kerrallaan tekstiä läpi. aikavaativuudessa ei oteta huomioon viestien kirjoittamista ja tallentamista levylle, mitkä vievät jo huomattavasti enenmmän aikaa. Aikavaativuuksien tarkempi analyysi löytyy [Testausdokumentaatiosta.](https://github.com/Antiik91/d3_ciph3r/blob/master/dokumentaatio/Testausdokumentti.md)
Tilavaativuudet riippuvat viestin koosta, eli molemmissa algoritmeissä ne ovat O(m)
	
# Puutteet ja parannusehdotukset.
* DES Algoritmistä jäi puuttumaan Substitution metodi, joka käytännössä vahvistaa algoritmin salausta huomattavasti. Sen 	 toteuttaminen oli itselleni ylivoimaisen haastavaa, enkä näihin aikamääreisiin pystynyt sitä toteuttamaan. 
* Ääkköset toimivat satunnaisilla koneilla, joissain ei toimi ollenkaan, joissain toimii moitteetta DESn kanssa. caesar Cipherin kanssa ääkköset eivät toimi. 
* Kaksi DES testiä epäonnistuu ubuntua käyttäessä, kyseessä ovat erikoismerkkien(!"#¤%&/()=? etc.) salaus ja purku testaukset. Windowsilla ne toimivat moitteetta.

# Lähteet
http://page.math.tu-berlin.de/~kant/teaching/hess/krypto-ws2006/des.htm

https://fi.wikipedia.org/wiki/DES
	
