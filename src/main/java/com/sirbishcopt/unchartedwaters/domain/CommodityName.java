package com.sirbishcopt.unchartedwaters.domain;

public enum CommodityName {
    AGATE("Agate"),
    ALCOHOL("Alcohol"),
    BANANAS("Banana"),
    CARPETS("Carpet"),
    CLOTH("Cloth"),
    DIAMONDS("Diamond"),
    DYE("Dye"),
    FIREARMS("Firearm"),
    FISH("Fish"),
    GOLD("Gold"),
    LEATHER("Leather"),
    MEAT("Meat"),
    MEDICINE("Medicine"),
    PAPER("Paper"),
    PEANUTS("Peanut"),
    PEARLS("Pearl"),
    PORCELAIN("Porcelain"),
    TEA_LEAVES("Tea"),
    TIN("Tin"),
    TOBACCO("Tobacco");

    private final String abbrev;

    CommodityName(String name) {
        this.abbrev = name;
    }

    public String toString() {
        return this.abbrev;
    }

}