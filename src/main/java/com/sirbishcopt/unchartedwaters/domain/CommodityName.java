package com.sirbishcopt.unchartedwaters.domain;

import java.util.stream.Stream;

public enum CommodityName {
    AGATE("Agat", "Agate"),
    ALCOHOL("Alcoh", "Alcohol"),
    BANANAS("Banan", "Bananas"),
    CARPETS("Carpe", "Carpets"),
    CLOTH("Clot", "Cloth"),
    DIAMONDS("Diamon", "Diamonds"),
    DYE("Dye", "Dye"),
    FIREARMS("Fire", "Firearms"),
    FISH("Fish", "Fish"),
    GOLD("Gold", "Gold"),
    LEATHER("Leather", "Leather"),
    MEAT("Meat", "Meat"),
    MEDICINE("Medic", "Medicine"),
    PAPER("Paper", "Paper"),
    PEANUTS("Peanut", "Peanuts"),
    PEARLS("Pearl", "Pearls"),
    PORCELAIN("Porcel", "Porcelain"),
    TEA_LEAVES("Tea", "Tea Leaves"),
    TIN("Tin", "Tin"),
    TOBACCO("Tobac", "Tobacco");

    private final String abbrev;
    private final String name;

    CommodityName(String abbrev, String name) {
        this.abbrev = abbrev;
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String toString() {
        return name;
    }

    public static Stream<CommodityName> stream() {
        return Stream.of(CommodityName.values());
    }

}