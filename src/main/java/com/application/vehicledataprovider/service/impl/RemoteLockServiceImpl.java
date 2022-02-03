package com.application.vehicledataprovider.service.impl;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import com.application.vehicledataprovider.data.repository.VehicleLockStatusRepository;
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

    public VehicleLockStatus getVehicleLockStatus(String VIN){
        Optional<VehicleLockStatus> vehicleLockStatusOptional = vehicleLockStatusRepository.findById(VIN);
        return vehicleLockStatusOptional.orElse(null);
    }

    public String createLockStatus(String VIN, String command){
        Optional<VehicleLockStatus> vehicleLockStatusOptional = vehicleLockStatusRepository.findById(VIN);
        if(vehicleLockStatusOptional.isPresent()) {
            VehicleLockStatus vehicleLockStatus = vehicleLockStatusOptional.get();
            return "Vehicle Lock Status entry already available. Cannot create new Entry. " +
                    "Please update existing entry using \nService Name: Update Lock Status\nHTTP Method: PUT\nURI: /api/v1/updateLockStatus/{VIN}/{COMMAND}";
        } else {
            vehicleLockStatusRepository.createLockStatus(VIN, getDBRemoteCommand(command), new Date(System.currentTimeMillis()));
            return "Vehicle Lock Status entry created successfully.";

        }
    }

    public String updateLockStatus(String VIN, String command){
        Optional<VehicleLockStatus> vehicleLockStatusOptional = vehicleLockStatusRepository.findById(VIN);
        if(vehicleLockStatusOptional.isPresent()) {
            vehicleLockStatusRepository.updateLockStatus(VIN, getDBRemoteCommand(command), new Date(System.currentTimeMillis()));
            return "Vehicle Status update Successful. Command: "+command;
        } else {
            return "Vehicle Lock Status not available. " +
                    "Please Please create entry using \nService Name: Create Lock Status\nHTTP Method: POST\nURI: /api/v1/createLockStatus/{VIN}/{COMMAND}";
        }
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
