package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class module_address_tab {
    @Id
    private int id;
    private String username;
    private String mobile;
    private String address;
}
