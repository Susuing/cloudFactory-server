package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
@NamedQuery(name = "module_order_tab.findByUserid",query = "SELECT o FROM module_order_tab o WHERE o.userid = ?1 ")
public class module_order_tab {
    @Id
    private String id;
    private String orderno;
    private String productid;
    private String ordernum;
    private String deaddate;
    private String deliverdate;
    private String orderstatus;
    private String userid;
    private String factoryid;
    private String receipt;
    private String contact;
    private String address;
}
