package com.neuedu.cloudfactoryvue.entity;

import javax.persistence.Id;

public class Register {

    @Id
    private String id;
    private String login_name;
    private String password;
    private String user_name;
    private String mobile;
    private String roleid;
    private String facid;
    private String factoryname;
    private String introduction;
    private String status;

    public module_factory_tab getFactory(){
        return new module_factory_tab(this.facid,this.factoryname,this.introduction,this.id,this.status);
    }
    public frame_user_tab getUser(){
        return new frame_user_tab(this.id,this.login_name,this.password,this.user_name,this.mobile,this.roleid);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRoleid() {
        return roleid;
    }

    public String getFacid() {
        return facid;
    }

    public String getFactoryname() {
        return factoryname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
