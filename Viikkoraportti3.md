Tällä viikolla keskityin alkuperäisestä suunnitelmastani poiketen UIn ja sen logiikan tekoon. Ajattelin tämän olevan olennaisempaa tähän hätään,
sillä ensi viikolla alkavat ensimmäiset koodikatselmoinnit, ja halusin että ohjelma on ajettavassa kunnossa. Samalla aloittelin myös
tiedostonhallintaan liittyviä ominaisuuksia, jotta ne olisivat valmiina kun pääsen testaamaan DESiä. Caesarin kanssa ne toimivatkin.

DESin puolelta alustin tarvittavat lookup taulukot, ja aloittelin keygenin luomista. Ajattelin sisällyttää avaimen luonnin DESin sisään 
oman luokan sijasta, sillä ilmeisesti sille riittää vain pari riviä koodia.  Aloitin myös aliavaimien luomiseen liittyvän metodin ja siinä
törmäsinkin ensimmäiseen ihmettelyn aiheeseen. Tarkoituksenani on selvittää, miten saan aliavamiet luotua toimivasti.
Tarkoituksenahan on ensiksi alkuperäinen avain ( 64 bittiä, eli 8 kirjainta, kuten avaimeni on tällä hetkellä. ) muuttaa bittijonoksi, 
ja vaihtaa PC-1 Lookup tabelin mukaan bittien järjestystä. Esim. PC1 ensimmäinen alkio on 59 eli otetaan avaimen K
59. bitti ja asetetaan se uuden avaimen K+ ensimäiseksi alkioksi. Käydään PC1 samalla tavalla läpi, jolloin saadaan Uusi avain K+ 
joka on 56 bittiä( joka 8. bitti jää huomiotta ).  Siitä lähetään sitten luomaan aliavaimia..

Ongelmani on siis miten tämä saadaan järkevästi toteutettua javalla. byte[] ei taida siihen kelvata vai kelpaako? Tässä tulee vastaan juurikin tämä, 
etten ole aikaisemmin oikein joutunut pelaamaan bittien kanssa. Suurin osa koodista on kuitenkin tätä, eli kun saan tämän selville, uskon että 
muutkin ongelmat jatkossa ratkeavat helpommin. Voi myös olla että ajattelen tämän turhan monimutkaisesti ja ongelmaan on yksinkertainen ratkaisu..

Aikaa tällä viikolla on kulunut 12 tuntia, joista ohjelmointiin 6.

 
