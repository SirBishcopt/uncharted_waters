package com.sirbishcopt.unchartedwaters.domain;

import java.util.stream.Stream;

public enum CityName {

    ADEN("Aden", "Aden"),
    ALEXANDRIA("Alex", "Alexandria"),
    AMSTERDAM("Ams", "Amsterdam"),
    ATHENS("Ath", "Athens"),
    BASRAH("Bas", "Basrah"),
    BOSTON("Bos", "Boston"),
    BRUNEI("Bru", "Brunei"),
    BUENOS_AIRES("Bue", "Buenos Aires"),
    CALICUT("Cal", "Calicut"),
    CAPE_TOWN("Cap", "Cape Town"),
    CAYENNE("Cay", "Cayenne"),
    CEYLON("Cey", "Ceylon"),
    COPENHAGEN("Cop", "Copenhagen"),
    DARWIN("Dar", "Darwin"),
    EDO("Edo", "Edo"),
    HAMBURG("Ham", "Hamburg"),
    HANGZHOU("Han", "Hangzhou"),
    ISTANBUL("Ist", "Istanbul"),
    JAMAICA("Jam", "Jamaica"),
    KOLKATA("Kol", "Kolkata"),
    LAS_PALMAS("Pal", "Las Palmas"),
    LISBON("Lis", "Lisbon"),
    LONDON("Lond", "London"),
    LUANDA("Lua", "Luanda"),
    MALACCA("Mal", "Malacca"),
    MANILA("Man", "Manila"),
    MARSEILLE("Mar", "Marseille"),
    MOZAMBIQUE("Moz", "Mozambique"),
    NANTES("Nan", "Nantes"),
    NASSAU("Nas", "Nassau"),
    PANAMA_CITY("Pan", "Panama City"),
    PINJARRA("Pin", "Pinjarra"),
    QUANZHOU("Qua", "Quanzhou"),
    RIO_DE_JANEIRO("Rio", "Rio de Janeiro"),
    SANTO_DOMINGO("Dom", "Santo Domingo"),
    SEVILLE("Sev", "Seville"),
    ST_GEORGES("Geo", "St. George's"),
    STOCKHOLM("Stoc", "Stockholm"),
    TUNIS("Tun", "Tunis"),
    VENICE("Ven", "Venice");

    private final String abbrev;
    private final String name;

    CityName(String abbrev, String name) {
        this.abbrev = abbrev;
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String toString() {
        return name;
    }

    public static Stream<CityName> stream() {
        return Stream.of(CityName.values());
    }

}