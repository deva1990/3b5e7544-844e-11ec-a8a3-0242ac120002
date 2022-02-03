package com.application.vehicledataprovider.controller;


import com.application.vehicledataprovider.data.entity.FeatureData;
import com.application.vehicledataprovider.exception.FeatureServiceException;
import com.application.vehicledataprovider.service.GetFeatureDataService;
import com.application.vehicledataprovider.controller.model.ValidFeatureResponse;
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
public class GetFeatureDataController {

  @Autowired
  private GetFeatureDataService featureDataService;


  @RequestMapping(value = "/getAllFeatures", method = RequestMethod.GET)
  public ResponseEntity<Object> getAllFeatures() {
    try {
      List<FeatureData> allFeatures = featureDataService.getAllFeatures();
      if (!allFeatures.isEmpty()) {
        return ResponseEntity.status(HttpStatus.OK).body(allFeatures);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Features Found in DB");

      }
    } catch (FeatureServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error getting vehicle features");
    }

  }

  @RequestMapping(value = "/isValidFeature/{VIN}/{FTR_CODE}", method = RequestMethod.GET)
  public ResponseEntity<Object> isValidFeature(@PathVariable("VIN") String VIN,
      @PathVariable("FTR_CODE") String featureCode) {
    boolean validFeature = false;
    try {
      validFeature = featureDataService.isValidFeature(VIN, featureCode);
      ValidFeatureResponse validFeatureResponse = new ValidFeatureResponse(VIN, featureCode,
          validFeature);
      return ResponseEntity.status(HttpStatus.OK).body(validFeatureResponse);
    } catch (FeatureServiceException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error checking isValidFeature");
    }
  }
}
