package com.application.vehicledataprovider.service;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import com.application.vehicledataprovider.exception.RemoteLockServiceException;

public interface RemoteLockService {

  VehicleLockStatus getVehicleLockStatus(String VIN) throws RemoteLockServiceException;
  boolean updateLockStatus(String VIN, String command) throws RemoteLockServiceException;
  boolean validateCommand(String command);

}
