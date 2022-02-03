package com.application.vehicledataprovider.data.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "FTR_DATA")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeatureData {

    @Id
    @Column(name = "FTR_CODE")
    private String featureCode;
    @Column(name = "FTR_DESC")
    private String featureDesc;
    @Column(name = "CREATE_USER")
    private String createdUser;
    @Column(name = "CREATE_S")
    private Date createdDate;
    @Column(name = "LAST_UPDT_USER")
    private String lastUpdatedUser;
    @Column(name = "LAST_UPDT_S")
    private Date lastUpdatedDate;
}
