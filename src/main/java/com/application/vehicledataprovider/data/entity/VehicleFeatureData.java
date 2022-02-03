package com.application.vehicledataprovider.data.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "VEH_FTR_DATA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleFeatureData {

    @Id
    @Column(name = "VEH_FTR_D")
    private Long VEH_FTR_D;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "VIN", nullable = false)
    private VehicleData vehicleData;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FTR_CODE", nullable = false)
    private FeatureData featureData;

    @Column(name = "CREATE_USER")
    private String createdUser;

    @Column(name = "CREATE_S")
    private Date createdDate;

    @Column(name = "LAST_UPDT_USER")
    private String lastUpdatedUser;

    @Column(name = "LAST_UPDT_S")
    private Date lastUpdatedDate;

}
