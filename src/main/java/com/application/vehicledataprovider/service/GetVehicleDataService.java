package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.VehicleData;

import com.application.vehicledataprovider.exception.VehicleServiceException;
import java.util.List;

public interface GetVehicleDataService {

    public List<VehicleData> getAllVehicleData() throws VehicleServiceException;

    public VehicleData getVehicleData(String VIN) throws VehicleServiceException;
}
