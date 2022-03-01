package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class module_device_product_tab {
    @Id
    private String deviceid;
    private String productid;
    private String capacity;
}
