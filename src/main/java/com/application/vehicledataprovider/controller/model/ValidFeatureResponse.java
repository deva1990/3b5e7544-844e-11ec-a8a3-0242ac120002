package com.application.vehicledataprovider.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ValidFeatureResponse {

  private String VIN;

  private String featureCode;

  private boolean valid;

}
