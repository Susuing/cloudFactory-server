package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class module_factory_tab {
    @Id
    private String id;
    private String factoryname;
    private String introduction;
    private String userid;
    private String status;

    public module_factory_tab(String id, String factoryname, String introduction, String userid, String status) {
        this.id = id;
        this.factoryname = factoryname;
        this.introduction = introduction;
        this.userid = userid;
        this.status = status;
    }

    public module_factory_tab() {

    }
}
