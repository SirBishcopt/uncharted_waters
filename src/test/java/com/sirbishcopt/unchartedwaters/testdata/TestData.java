package com.sirbishcopt.unchartedwaters.testdata;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;

public class TestData {

    public static final String OCR_TEXT =
            """
                    Tobacco
                    Price 549 (110%)
                    Profit/Loss +23.4%
                    Weight 50
                    Agate
                    Price 1.063K (106%)
                    Profit/Loss +21.8%
                    Weight 100
                    Leather
                    Price 219 (110%)
                    Profit/Loss +18.4%
                    Weight 16
                    Fish
                    Price 72 (103%)
                    Profit/Loss +12.5%
                    Weight 5
                    Meat
                    Price 85 (94%)
                    Profit/Loss +4.9%
                    Weight 6
                    Pearls
                    Price 684 (91%)
                    Profit/Loss +3%
                    Weight 68
                    Alcohol
                    Price 921 (108%)
                    Profit/Loss +23.1%
                    Weight 65
                    Medicine
                    Price 159 (106%)
                    Profit/Loss +18.7%
                    Weight 11
                    Paper
                    Price 134 (103%)
                    Profit/Loss +12.6%
                    Weight 13
                    Gold
                    Price 864 (96%)
                    Profit/Loss +7.7%
                    Weight 75
                    Porcelain
                    Price 745 (93%)
                    Profit/Loss +4.3%
                    Weight 57
                    Carpets
                    Price 395 (99%)
                    Profit/Loss +2.3%
                    Weight 30
                    Meat
                    Price 85 (94%)
                    Profit/Loss +4.9%
                    Weight 6
                    Pearls
                    Price 684 (91%)
                    Profit/Loss +3%
                    Weight 68
                    Dye
                    Price 283 (94%)
                    Profit/Loss +2.2%
                    Weight 20
                    Tea Leaves
                    Price 393 (87%)
                    Profit/Loss +2 1%
                    Weight 37
                    Tin
                    Price 331 (95%)
                    Profit/Loss +0%
                    Weight 25
                    Firearms
                    Price 1.015K (92%)
                    Profit/Loss —3.7%
                    Weight 100
                    Porcelain
                    Price 745 (93%)
                    Profit/Loss +4.3%
                    Weight 57
                    Carpets
                    Price 395 (99%)
                    Profit/Loss +2.3%
                    Weight 30
                    Bananas
                    Price 48 (96%)
                    Profit/Loss +2.1%
                    Weight 4
                    Cloth
                    Price 229 (92%)
                    Profit/Loss +0.4%
                    Weight 22
                    Diamonds
                    Price 1.132K (94%)
                    Profit/Loss —0.2%
                    Weight 80
                    Peanuts
                    Price 104 (95%)
                    Profit/Loss —8.9%
                    Weight 10
                    """;

    public static final City EXPECTED_CITY = new City(CityName.EDO);

    static {
        EXPECTED_CITY.getCommodityByName(CommodityName.TOBACCO).setPrice(549);
        EXPECTED_CITY.getCommodityByName(CommodityName.AGATE).setPrice(1063);
        EXPECTED_CITY.getCommodityByName(CommodityName.LEATHER).setPrice(219);
        EXPECTED_CITY.getCommodityByName(CommodityName.FISH).setPrice(72);
        EXPECTED_CITY.getCommodityByName(CommodityName.MEAT).setPrice(85);
        EXPECTED_CITY.getCommodityByName(CommodityName.PEARLS).setPrice(684);
        EXPECTED_CITY.getCommodityByName(CommodityName.ALCOHOL).setPrice(921);
        EXPECTED_CITY.getCommodityByName(CommodityName.MEDICINE).setPrice(159);
        EXPECTED_CITY.getCommodityByName(CommodityName.PAPER).setPrice(134);
        EXPECTED_CITY.getCommodityByName(CommodityName.GOLD).setPrice(864);
        EXPECTED_CITY.getCommodityByName(CommodityName.PORCELAIN).setPrice(745);
        EXPECTED_CITY.getCommodityByName(CommodityName.CARPETS).setPrice(395);
        EXPECTED_CITY.getCommodityByName(CommodityName.DYE).setPrice(283);
        EXPECTED_CITY.getCommodityByName(CommodityName.TEA_LEAVES).setPrice(393);
        EXPECTED_CITY.getCommodityByName(CommodityName.TIN).setPrice(331);
        EXPECTED_CITY.getCommodityByName(CommodityName.FIREARMS).setPrice(1015);
        EXPECTED_CITY.getCommodityByName(CommodityName.BANANAS).setPrice(48);
        EXPECTED_CITY.getCommodityByName(CommodityName.CLOTH).setPrice(229);
        EXPECTED_CITY.getCommodityByName(CommodityName.DIAMONDS).setPrice(1132);
        EXPECTED_CITY.getCommodityByName(CommodityName.PEANUTS).setPrice(104);
    }

}