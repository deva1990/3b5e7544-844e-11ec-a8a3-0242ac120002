package com.application.vehicledataprovider.controller;

import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import com.application.vehicledataprovider.service.RemoteLockService;
import com.application.vehicledataprovider.service.impl.RemoteLockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class RemoteLockController {

    @Autowired
    private RemoteLockService remoteLockService;

    @RequestMapping(value = "/lockStatus/{VIN}", method = RequestMethod.GET)
    public ResponseEntity<Object> getVehicleLockStatus(@PathVariable("VIN") String VIN) {
        try {
            VehicleLockStatus vehicleLockStatus = remoteLockService.getVehicleLockStatus(VIN);
            if(vehicleLockStatus != null) {
                return ResponseEntity.status(HttpStatus.OK).body(vehicleLockStatus);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lock status not found for VIN: "+VIN);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred while fetching Lock Status for VIN: "+VIN+". Exception Message: "+ex.getMessage());
        }
    }

    @RequestMapping(value = "/createLockStatus/{VIN}/{COMMAND}", method = RequestMethod.POST)
    public ResponseEntity<Object> createLockStatus(@PathVariable("VIN") String VIN, @PathVariable("COMMAND") String command) {
        if(remoteLockService.validateCommand(command)) {
            String response = remoteLockService.createLockStatus(VIN, command);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Received Invalid Command: "+command);
        }
    }

    @RequestMapping(value = "/updateLockStatus/{VIN}/{COMMAND}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateLockStatus(@PathVariable("VIN") String VIN, @PathVariable("COMMAND") String command) {
        if(remoteLockService.validateCommand(command)) {
            String response = remoteLockService.updateLockStatus(VIN, command);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Received Invalid Command: "+command);
        }
    }
}
