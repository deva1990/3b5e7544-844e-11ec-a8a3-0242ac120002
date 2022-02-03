package com.application.vehicledataprovider.data.repository;

import com.application.vehicledataprovider.data.entity.VehicleData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleDataRepository extends CrudRepository<VehicleData, String> {





}
