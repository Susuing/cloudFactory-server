package com.neuedu.cloudfactoryvue.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class frame_role_tab {
    @Id
    private int id;
    private String rolename;
    private String remark;
}
