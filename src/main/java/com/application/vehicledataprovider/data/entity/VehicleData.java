package com.application.vehicledataprovider.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "VEH_DATA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleData {

    @Id
    @Column(name = "VIN")
    private String VIN;
    @Column(name = "OWNER_NAME")
    private String OwnerName;
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;
    @Column(name = "LAST_SERVICE_DATE")
    private Date lastServiceDate;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "IS_TCU_ENABLED")
    private String isTCUEnabled;
    @Column(name = "CREATE_USER")
    private String createdUser;
    @Column(name = "CREATE_S")
    private Date createdDate;
    @Column(name = "LAST_UPDT_USER")
    private String lastUpdatedUser;
    @Column(name = "LAST_UPDT_S")
    private Date lastUpdatedDate;
}
