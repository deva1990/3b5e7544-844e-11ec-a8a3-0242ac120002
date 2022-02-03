package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.FeatureData;

import com.application.vehicledataprovider.exception.FeatureServiceException;
import java.util.List;

public interface GetFeatureDataService {

    public List<FeatureData> getAllFeatures() throws FeatureServiceException;

    public boolean isValidFeature(String VIN, String featureCode) throws FeatureServiceException;

}
