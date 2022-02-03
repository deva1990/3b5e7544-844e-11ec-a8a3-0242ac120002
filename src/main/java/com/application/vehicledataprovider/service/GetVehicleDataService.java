package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.VehicleData;

import java.util.List;

public interface GetVehicleDataService {

    public List<VehicleData> getAllVehicleData();

    public VehicleData getVehicleData(String VIN);
}
