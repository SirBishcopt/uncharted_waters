package com.sirbishcopt.unchartedwaters.service.collecting;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Commodity;

import java.util.List;

public interface StringToObjectConverter {

    public CityName convertStringToCityName (String ocrName);

    public List<Commodity> convertStringToCommodities (String ocrCommodities);

}