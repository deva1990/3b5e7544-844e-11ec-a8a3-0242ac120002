package com.application.vehicledataprovider.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name = "VEH_LOCK_STATUS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleLockStatus {

    @Id
    @Column(name = "VIN", unique = true, nullable = false)
    private String VIN;
    @Column(name = "IS_LOCKED")
    private String isLocked;
    @Column(name = "CREATE_USER")
    private String createdUser;
    @Column(name = "CREATE_S")
    private Date createdDate;
    @Column(name = "LAST_UPDT_USER")
    private String lastUpdatedUser;
    @Column(name = "LAST_UPDT_S")
    private Date lastUpdatedDate;
}
