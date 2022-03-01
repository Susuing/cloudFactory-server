package com.neuedu.cloudfactoryvue.entity;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
@NamedQuery(name = "module_bid_tab.findByOrderid",query = "SELECT bid FROM module_bid_tab bid WHERE bid.orderid = ?1 ")
public class module_bid_tab {
    @Id
    private String id;
    private String orderid;
    private String factoryid;
    private String bidprice;
    private String bidstatus;
}
