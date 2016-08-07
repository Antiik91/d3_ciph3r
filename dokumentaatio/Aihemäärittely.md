#Aihe

Tekstin kryptaus ja enkryptausohjelma.  Toteutetaan ohjelma, jonka avulla
voidaan salata ja purkaa tekstejä eri salausmenetelmillä.

Projektissa tulee olemaan teksitkäyttöliittymä. Graafista käyttöliittymää ei tulla toteuttamaan.

#Ohjelma

Ohjelmassa voit salata tekstitiedoston sisällön käyttäen joko Caesar Cipheriä [wikipedia](https://en.wikipedia.org/wiki/Caesar_cipher) tai Data Encryption Standardia [wikipedia](https://en.wikipedia.org/wiki/Data_Encryption_Standard).  
Vastaavasti voit purkaa salauksen oikealla avaimella.

#Aika- ja tilavaativuus

Normaalisti DES:n aikavaativuus on O(1), jos suoritetaan block salausta [wikipedia](https://en.wikipedia.org/wiki/Block_cipher), eli salataan tietyn kokoisia "bittijoukkoja".
Toisaalta, jos DES suoritetaan mode of operationissa [wikipedia](https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation) salatakseen pidempiä tekstejä, tulee aikavaativuudeksi O(m), missä m on viestin pituus. Eli voidaan ajatella, että sinulla on m kappaletta "bittijoukkoja". Tilavaativuus on myös O(m).

Caesar Cipherissa aika- ja tilavaativuudet ovat O(m) algoritmi salataan vaihtamalla jokainen kirjain toiseen antamalla shift määrä, jonka verran aakkosista siirretään vasemmalle kyseisestä kirjaimesta.

#Luokkakaavio(karkea)

![määrittelyvaiheen_luokkakaavio](http://yuml.me/2e1d4e94)
