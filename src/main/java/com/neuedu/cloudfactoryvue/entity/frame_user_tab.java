package com.neuedu.cloudfactoryvue.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@NamedQuery(name = "frame_user_tab.findByLogin_name",query = "SELECT u FROM frame_user_tab u WHERE u.login_name = ?1 ")
public class frame_user_tab {
    @Id
    private String id;
    private String login_name;
    private String password;
    private String user_name;
    private String mobile;
    private String roleid;

    public frame_user_tab(String id, String login_name, String password, String user_name, String mobile, String roleid) {
        this.id = id;
        this.login_name = login_name;
        this.password = password;
        this.user_name = user_name;
        this.mobile = mobile;
        this.roleid = roleid;
    }

    public frame_user_tab() {

    }
}
