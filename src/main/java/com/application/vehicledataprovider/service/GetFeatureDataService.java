package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.FeatureData;

import java.util.List;

public interface GetFeatureDataService {

    public List<FeatureData> getAllFeatures();

    public boolean isValidFeature(String VIN, String featureCode);

}
