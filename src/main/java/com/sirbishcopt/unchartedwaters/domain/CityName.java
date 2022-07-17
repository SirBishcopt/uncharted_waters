package com.sirbishcopt.unchartedwaters.domain;

public enum CityName {

    ADEN("Aden"),
    ALEXANDRIA("Alexandria"),
    AMSTERDAM("Amsterdam"),
    ATHENS("Athens"),
    BASRAH("Basrah"),
    BOSTON("Boston"),
    BRUNEI("Brunei"),
    BUENOS_AIRES("Buenos Aires"),
    CALICUT("Calicut"),
    CAPE_TOWN("Cape Town"),
    CAYENNE("Cayenne"),
    CEYLON("Ceylon"),
    COPENHAGEN("Copenhagen"),
    DARWIN("Darwin"),
    EDO("Edo"),
    HAMBURG("Hamburg"),
    HANGZHOU("Hangzhou"),
    ISTANBUL("Istanbul"),
    JAMAICA("Jamaica"),
    KOLKATA("Kolkata"),
    LAS_PALMAS("Las Palmas"),
    LISBON("Lisbon"),
    LONDON("London"),
    LUANDA("Luanda"),
    MALACCA("Malacca"),
    MANILA("Manila"),
    MARSEILLE("Marseille"),
    MOZAMBIQUE("Mozambique"),
    NANTES("Nantes"),
    NASSAU("Nassau"),
    PANAMA_CITY("Panama City"),
    PINJARRA("Pinjarra"),
    QUANZHOU("Quanzhou"),
    RIO_DE_JANEIRO("Rio de Janeiro"),
    SANTO_DOMINGO("Santo Domingo"),
    SEVILLE("Seville"),
    ST_GEORGES("St. George's"),
    STOCKHOLM("Stockholm"),
    TUNIS("Tunis"),
    VENICE("Venice");

    private final String name;

    CityName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}