package com.application.vehicledataprovider.service.impl;

import com.application.vehicledataprovider.data.entity.VehicleData;
import com.application.vehicledataprovider.exception.VehicleServiceException;
import com.application.vehicledataprovider.service.GetVehicleDataService;
import com.application.vehicledataprovider.data.repository.VehicleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetVehicleDataServiceImpl implements GetVehicleDataService {

    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    public List<VehicleData> getAllVehicleData() throws VehicleServiceException {
        List<VehicleData> allVehicleData;
        try {
            allVehicleData = (List<VehicleData>) vehicleDataRepository.findAll();
        } catch (Exception ex) {
            throw new VehicleServiceException("Error getting all vehicle data");
        }
        return allVehicleData;
    }

    public VehicleData getVehicleData(String VIN) throws VehicleServiceException {
        try {
            Optional<VehicleData> vehicleDataOptional = vehicleDataRepository.findById(VIN);
            return vehicleDataOptional.orElse(null);
        }catch (Exception ex){
            throw new VehicleServiceException("Error getting vehicle data");
        }
    }

}
