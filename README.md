<h1> Travel Agency</h1>

<h2>Descriere</h2>
<p>Aceasta este o aplicație de gestionare a unei agenții de turism, care permite adăugarea, editarea și ștergerea de zboruri, taxe de oraș, hoteluri și vacanțe. Pe lângă aceste posibilităti avem promoții de tip „Last Minute” pentru vcacanțe, cât si căutarea unitășilor de cazare în funcție de oraș. </p>

<p>Aplicația include un serviciu de audit care înregistrează fiecare acțiune executată într-un fișier CSV.</p>

<h2>Conecsiune cu baza de date</h2>

<p>Taxa de Oras, informatiile despre Unitatile de cazare si zborurile le tinem intr-o baza de date locala , si in programul nostru apelam queriuri pe baza de date pentru a afisa, a modifica, a introduce si a sterge date din tabela</p>

<h5>City Tax</h5>
<ul>
  <li>idcity_tax int AI PK</li>
  <li>country varchar(50)</li>
  <li>city varchar(50)</li>
  <li>tax double</li>
</ul>

<h5>Flights</h5>
<ul>
  <li>idplane int AI PK</li>
  <li>price double</li>
  <li>moment_of_flight datetime</li>
  <li>moment_of_arrival datetime</li>
  <li>airport_city varchar(45)</li>
  <li>airport_arrival varchar(45)</li>
</ul>

<h5>Hotel</h5>
<ul>
  <li>id int AI PK</li>
  <li>name varchar(255)</li>
  <li>nrStele int</li>
  <li>tara varchar(100)</li>
  <li>city varchar(100)</li>
  <li>tipMasa enum('all_inclusive','breakfast','half_board')</li>
  <li>nr_piscine int</li>
</ul>



The price of the vacation = flight1 + flight2 + nr_of_nights_of_the_trip * (City_Tax + Room_price)
This is the menu 

<h3>Meniu si functionalitati</h3>

<ul>
  <li>1: Add Flight</li>
  <li>2: List all Flights by price</li>
  <li>3: Add a City Taxes</li>
  <li>4: Show all Cities Taxes</li>
  <li>5: Edit City Taxes</li>
  <li>6: Add a Hotel</li>
  <li>7: Show All Hotels</li>
  <li>8: Find Hotel in a City</li>
  <li>9: Create a room in a Hotel</li>
  <li>10: Add a Vacations</li>
  <li>11: SHOW all Vacation</li>
  <li>12: LAST MINUTE OFFER</li>
 <li>13: Sterge ZBOR</li>
 <li>14: Sterge Taxa</li>
 <li>15: EDIT ZBOR</li>
 <li>0: Exit</li>
</ul>

<p>The price of the vacation = flight1 + flight2 + nr_of_nights_of_the_trip * (City_Tax + Room_price)</p>

