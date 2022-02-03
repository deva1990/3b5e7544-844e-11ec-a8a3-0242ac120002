package com.application.vehicledataprovider.data.repository;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;


@Repository
public interface VehicleLockStatusRepository extends CrudRepository<VehicleLockStatus, String> {

    @Modifying
    @Query(value = "INSERT INTO VEH_LOCK_STATUS VALUES (:VIN, :lockStatus, :updateDate)", nativeQuery = true)
    @Transactional
    public void createLockStatus(String VIN, String lockStatus, Date updateDate);

    @Modifying
    @Query(value = "UPDATE VEH_LOCK_STATUS SET IS_LOCKED=:lockStatus, LAST_UPDT_S=:updateDate WHERE VIN=:VIN", nativeQuery = true)
    @Transactional
    public void updateLockStatus(String VIN, String lockStatus, Date updateDate);
}
