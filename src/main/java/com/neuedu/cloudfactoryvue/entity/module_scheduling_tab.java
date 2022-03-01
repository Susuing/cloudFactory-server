package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class module_scheduling_tab {
    @Id
    private String id;
    private String orderid;
    private String deviceid;
    private String begindate;
    private String enddate;
}
