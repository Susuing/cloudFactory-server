package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class module_device_type_tab {
    @Id
    private String id;
    private String typename;
}
