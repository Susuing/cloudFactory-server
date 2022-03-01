package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class module_rent_tab {
    @Id
    private String id;
    private Date begindate;
    private Date enddate;
    private String length;
    private String deviceid;
    private String factoryid;
}
