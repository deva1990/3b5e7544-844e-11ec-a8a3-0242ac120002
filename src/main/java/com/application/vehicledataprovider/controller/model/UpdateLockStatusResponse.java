package com.application.vehicledataprovider.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UpdateLockStatusResponse {

  private String VIN;
  private String command;
  private int statusCode;
  private String statusMsg;

}
