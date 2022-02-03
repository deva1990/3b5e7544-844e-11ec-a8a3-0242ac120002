package com.application.vehicledataprovider.data.repository;

import com.application.vehicledataprovider.data.entity.VehicleFeatureData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface VehicleFeatureDataRepository extends CrudRepository<VehicleFeatureData, Serializable> {

    @Query(value = "SELECT * FROM VEH_FTR_DATA WHERE VIN=?1 AND FTR_CODE=?2",nativeQuery = true)
    public VehicleFeatureData getVehicleFeatureByVINAndFeatureCode(String VIN, String featureCode);
}
