package com.sirbishcopt.unchartedwaters.repository;

public class Repository {

    // dependencja do Spring JPA - to mam
    // dodać zależność do bazy danych (H2 - raczej testowa, Postgres (wygone z Heroku), MySQL, Oracle)
    // dostosować klasę encji (konstruktor, adnotacja, klucz główny, gettery settery)
    // zamiast sesji intefejs dziedziczący po CrudRepository lub JPARepository
    // tego interfejsu uzywa się przez wstrzykiwanie zależności
    // dodać konfiguację bazy danych w applicationproperties

    // na Heroku może być błąd z wersją javy; żeby Heroku wiedziało, jakiej werzsji Javy używam, w roocie projektu ma być plis system.properties (w środku java.runtime.version=17)

}