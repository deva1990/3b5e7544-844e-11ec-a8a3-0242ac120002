package com.application.vehicledataprovider.service.impl;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import com.application.vehicledataprovider.data.repository.VehicleLockStatusRepository;
import com.application.vehicledataprovider.exception.RemoteLockServiceException;
import com.application.vehicledataprovider.service.RemoteLockService;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteLockServiceImpl implements RemoteLockService {

    @Autowired
    private VehicleLockStatusRepository vehicleLockStatusRepository;

    public VehicleLockStatus getVehicleLockStatus(String VIN) throws RemoteLockServiceException {
        try {
            Optional<VehicleLockStatus> vehicleLockStatusOptional = vehicleLockStatusRepository.findById(
                VIN);
            return vehicleLockStatusOptional.orElse(null);
        }catch (Exception ex){
            throw new RemoteLockServiceException("Erorr getting vehicle lock status");
        }
    }

    public boolean updateLockStatus(String VIN, String command) throws RemoteLockServiceException {
        boolean isUpdateSuccess = false;
        try {
            Optional<VehicleLockStatus> vehicleLockStatusOptional = vehicleLockStatusRepository.findById(
                VIN);
            if (vehicleLockStatusOptional.isPresent()) {
                vehicleLockStatusRepository.updateLockStatus(VIN, getDBRemoteCommand(command),
                    new Date(System.currentTimeMillis()));
            } else {
                vehicleLockStatusRepository.createLockStatus(VIN, getDBRemoteCommand(command),
                    new Date(System.currentTimeMillis()));
            }
            isUpdateSuccess = true;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RemoteLockServiceException("Erorr updating vehicle lock status");
        }
        return isUpdateSuccess;
    }

    public boolean validateCommand(String command) {
        List<String> remoteCommands = Arrays.asList("LOCK", "UNLOCK");
        return remoteCommands.contains(command);
    }

    private String getDBRemoteCommand(String command) {
        if("LOCK".equalsIgnoreCase(command)) {
            return "Y";
        } else {
            return "N";
        }
    }

}
