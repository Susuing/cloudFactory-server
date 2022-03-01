package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
//@NamedQuery(name = 'module_device_tab.findRentMyDev',query = "sql")
public class module_device_tab {
    @Id
    private String id;
    private String typeid;
    private String devicename;
    private String devicenorms;
    private String devicedescribe;
    private String deviceno;
    private String devicestatus;
    private String rentstatus;
    private String factoryid;
}
