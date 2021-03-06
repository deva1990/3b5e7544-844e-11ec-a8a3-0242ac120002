package com.application.vehicledataprovider.controller;

import com.application.vehicledataprovider.controller.model.ValidVehicleResponse;
import com.application.vehicledataprovider.data.entity.VehicleData;
import com.application.vehicledataprovider.exception.VehicleServiceException;
import com.application.vehicledataprovider.service.GetVehicleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class GetVehicleDataController {

  @Autowired
  private GetVehicleDataService getVehicleDataService;


  @RequestMapping(value = "/getAllVehicles", method = RequestMethod.GET)
  public ResponseEntity<Object> getAllVehicles() {
    try {
      List<VehicleData> allVehicleData = getVehicleDataService.getAllVehicleData();
      if (!allVehicleData.isEmpty()) {
        return ResponseEntity.status(HttpStatus.OK).body(allVehicleData);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Vehicle Found in DB");
      }
    } catch (VehicleServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error getting the vehicle details from DB");
    }

  }


  @RequestMapping(value = "/getVehicle/{VIN}", method = RequestMethod.GET)
  public ResponseEntity<Object> getVehicle(@PathVariable("VIN") String VIN) {
    try {
      VehicleData vehicleData = getVehicleDataService.getVehicleData(VIN);
      if (vehicleData != null) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleData);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle Not Found");
      }
    } catch (VehicleServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error getting the vehicle detail from DB");

    }
  }

  @RequestMapping(value = "/isValidVehicle/{VIN}", method = RequestMethod.GET)
  public ResponseEntity<Object> isValidVehicle(@PathVariable("VIN") String VIN) {
    try {
      VehicleData vehicleData = getVehicleDataService.getVehicleData(VIN);
      ValidVehicleResponse validVehicleResponse;
      if (vehicleData != null) {
        validVehicleResponse = new ValidVehicleResponse(VIN, Boolean.TRUE);
      } else {
        validVehicleResponse = new ValidVehicleResponse(VIN, Boolean.FALSE);
      }
      return ResponseEntity.status(HttpStatus.OK).body(validVehicleResponse);
    } catch (VehicleServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error checking valid vehicle");

    }

  }


  @RequestMapping(value = "/isTCUEnabled/{VIN}", method = RequestMethod.GET)
  public ResponseEntity<Object> isTCUEnabled(@PathVariable("VIN") String VIN) {
    try {
      VehicleData vehicleData = getVehicleDataService.getVehicleData(VIN);
      if (vehicleData != null) {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleData.getIsTCUEnabled());
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle Not Found");
      }
    } catch (VehicleServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error when checking if TCU is enabled for the vehicle");

    }
  }
}
