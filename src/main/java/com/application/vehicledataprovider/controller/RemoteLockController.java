package com.application.vehicledataprovider.controller;

import com.application.vehicledataprovider.controller.model.UpdateLockStatusResponse;
import com.application.vehicledataprovider.data.entity.VehicleLockStatus;
import com.application.vehicledataprovider.exception.RemoteLockServiceException;
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
      if (vehicleLockStatus != null) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleLockStatus);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Lock status not found for VIN: " + VIN);
      }
    } catch (RemoteLockServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          "Exception occurred while fetching Lock Status for VIN: " + VIN + ". Exception Message: "
              + ex.getMessage());
    }
  }


  @RequestMapping(value = "/updateLockStatus/{VIN}/{COMMAND}", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateLockStatus(@PathVariable("VIN") String VIN,
      @PathVariable("COMMAND") String command) {
    try {
      if (remoteLockService.validateCommand(command)) {
        UpdateLockStatusResponse updateLockStatusResponse = null;
        if (remoteLockService.updateLockStatus(VIN, command)) {
          updateLockStatusResponse = new UpdateLockStatusResponse(
              VIN, command, 0, "Success");
        } else {
          updateLockStatusResponse = new UpdateLockStatusResponse(
              VIN, command, 10, "Error updating the status");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateLockStatusResponse);
      } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Received Invalid Command: " + command);
      }
    } catch (RemoteLockServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
          "Exception occurred while fetching Lock Status for VIN: " + VIN + ". Exception Message: "
              + ex.getMessage());
    }
  }
}
