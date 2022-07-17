package com.sirbishcopt.unchartedwaters.domain;

public enum CommodityName {
    AGATE("Agate"),
    ALCOHOL("Alcohol"),
    BANANAS("Bananas"),
    CARPETS("Carpets"),
    CLOTH("Cloth"),
    DIAMONDS("Diamonds"),
    DYE("Dye"),
    FIREARMS("Firearms"),
    FISH("Fish"),
    GOLD("Gold"),
    LEATHER("Leather"),
    MEAT("Meat"),
    MEDICINE("Medicine"),
    PAPER("Paper"),
    PEANUTS("Peanuts"),
    PEARLS("Pearls"),
    PORCELAIN("Porcelain"),
    TEA_LEAVES("Tea Leaves"),
    TIN("Tin"),
    TOBACCO("Tobacco");

    private final String name;

    CommodityName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}