package com.sirbishcopt.unchartedwaters.domain;

public enum CityName {

    ADEN("Aden"),
    ALEXANDRIA("Alex"),
    AMSTERDAM("Ams"),
    ATHENS("Ath"),
    BASRAH("Bas"),
    BOSTON("Bos"),
    BRUNEI("Bru"),
    BUENOS_AIRES("Bue"),
    CALICUT("Cal"),
    CAPE_TOWN("Cap"),
    CAYENNE("Cay"),
    CEYLON("Cey"),
    COPENHAGEN("Cop"),
    DARWIN("Dar"),
    EDO("Edo"),
    HAMBURG("Ham"),
    HANGZHOU("Han"),
    ISTANBUL("Ist"),
    JAMAICA("Jam"),
    KOLKATA("Kol"),
    LAS_PALMAS("Las"),
    LISBON("Lis"),
    LONDON("Lond"),
    LUANDA("Lua"),
    MALACCA("Mal"),
    MANILA("Man"),
    MARSEILLE("Mar"),
    MOZAMBIQUE("Moz"),
    NANTES("Nan"),
    NASSAU("Nas"),
    PANAMA_CITY("Pan"),
    PINJARRA("Pin"),
    QUANZHOU("Qua"),
    RIO_DE_JANEIRO("Rio"),
    SANTO_DOMINGO("San"),
    SEVILLE("Sev"),
    ST_GEORGES("Geo"),
    STOCKHOLM("Stoc"),
    TUNIS("Tun"),
    VENICE("Ven");

    private final String abbrev;

    CityName(String name) {
        this.abbrev = name;
    }

    public String toString() {
        return this.abbrev;
    }

}