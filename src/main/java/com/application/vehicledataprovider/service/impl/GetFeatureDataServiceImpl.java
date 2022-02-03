package com.application.vehicledataprovider.service.impl;

import com.application.vehicledataprovider.data.entity.FeatureData;
import com.application.vehicledataprovider.data.entity.VehicleFeatureData;
import com.application.vehicledataprovider.exception.FeatureServiceException;
import com.application.vehicledataprovider.service.GetFeatureDataService;
import com.application.vehicledataprovider.data.repository.FeatureDataRepository;
import com.application.vehicledataprovider.data.repository.VehicleFeatureDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFeatureDataServiceImpl implements GetFeatureDataService {

  @Autowired
  private FeatureDataRepository featureDataRepository;

  @Autowired
  private VehicleFeatureDataRepository vehicleFeatureDataRepository;

  @Override
  public List<FeatureData> getAllFeatures() throws FeatureServiceException {
    try {
      return (List<FeatureData>) featureDataRepository.findAll();
    } catch (Exception ex) {
      throw new FeatureServiceException("Error getting all feature data");
    }
  }

  @Override
  public boolean isValidFeature(String VIN, String featureCode) throws FeatureServiceException {
    try {
      VehicleFeatureData vehicleFeatureData = vehicleFeatureDataRepository.getVehicleFeatureByVINAndFeatureCode(
          VIN, featureCode);
      return vehicleFeatureData != null;
    } catch (Exception ex) {
      throw new FeatureServiceException("Error checking if the feature is valid");
    }
  }
}
