package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;

public interface RemoteLockService {

  VehicleLockStatus getVehicleLockStatus(String VIN);
  String createLockStatus(String VIN, String command);
  String updateLockStatus(String VIN, String command);
  boolean validateCommand(String command);

}
