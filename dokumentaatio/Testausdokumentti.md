# Mitä on testattu
	Ohjelmassa on testattu Käyttöliittymän ja logiikan toimivuutta
	Sekä käsin että JUnit testeillä, missä se on suotavaa
		...
	Caesar Cipherissa testataan erilaisilla muunnoksilla salauksen ja sen purkamisen toimivuutta,  pienistä muutoksita kokonaisten merkkijonojen muokkauksiin. Eri siirtymän käyttämällä. Lisäksi pyritään eliminoimaan käyttäjän vahingossa syöttämät negatiiviset merkit eteen.
	
	DESin kanssa testataan, että avaimen puuttuminen ei kaada ohjelmaa, vaan silloin luodaan uusi avain, jota on käytetty salauksessa tai purussa. Lisäksi testataan avaingeneraattorin luomia avaimia, sillä on erittäin tärkeä että ne ovat 8 kirjaimisia. 
	
# Syötteet
	Syötteinä on käytetty eripituisia merkkijonoja sekä tekstitiedostoja. Caesar Cipherissa ei toimi ÄÄKKÖSET joten käytän siinä englantia testauksissa. 

# Toistettavuus
	...
	

# Empiirinen testaus
	... 
