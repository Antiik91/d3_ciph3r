Tällä viikolla sain projektini sellaiseen kuntoon että pääsin testaamaan vihdoinkin salauksia ja salauksien purkamista DES kanssa. Vaan sitten alkoivat ongelmat.

Aluksi en saanut tekstiä salattua kuin yhden tavun, joka oli ensimmäinen, muut nollia. Eli jos esim alkuperäinen data tavuina oli (desimaalilukuina, kuten javassa)
13, 102, 244, 12, ... n. Niin salattu teksti oli esimerkiksi 4, 0 , 0 , 0 , 0 .... 0. Ongelma oli aliavainten luomisessa ja yhdistelyssä, jonka sain korjattua,
jolloin alkoi tulla salaukseen pidempiä bittisarjoja, mutta salauksen purku ei toimi itselleni tuntemattomasta syystä ollenkaan.  
En osaa tarkalleen paikallistaa vikaa. Ensiksi ajattelin sen olevan substitution metodissa, sen poistaminen ei asiaa korjannut valitetavasti. 
Substitutionin poisjättäminenhän ei riko varsinaisesti DESia, mutta tekee siitä erittäin suoraviivaisen ja sitä mukaa helposti murrettavan.

Olen siinä käsityksessä, että salauksen purkuun tarvitaan aliavaimien käyttöä päinvastaisessa järjestyksessä, vaan eipä toimi. 


Ongelma on tällä hetkellä tuntematon, mutta jatkan debuggaamista, ja minut nähdään pajassa varmasti ihmettelemässä. 

Keskityin myös testien lisäämiseen tällä viikolla, sillä ne oli jääneet varjoon. Loin myös ArrayCopy luokan jossa tällä hetkellä (mahdollisesti ainoa projektiin tarvittava taulukkokopiointi)
staattinen metodi, joka toteuttaa System.arraycopyn toiminnallisuuden byte[] taulukoilla. Huomasin myös vertaisarvioineen kaverin keskittyvän 
AES luokkaan jonka oli määrä poistaa jo aikoja sitten mutta unohtui tiimellyksessä.

Tällä viikolla opin että kaikki ei aina ole niin helppoa kuin näyttää. Tässä projektissa olen joutunut ottamaan niin monta eri asiaa huomioon, että ei tullut mieleenkikään 
silloin kuin projektia valitsin, minkälainen työmaa tämä on. Toisaalta, on ollut myös opettavainen kokemus. 

Aikaa on kulunut tällä viikolla 18h, koodaamiseen / ja debuggaamiseen mennyt aikaa noin 14h.
