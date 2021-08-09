package com.lant.www.info;

import java.util.Date;
import java.util.List;

public class AdminInfo {
    //尽量实体类属性名和数据表字段名能够保持一致;
    private int id;
    private String acount;
    private String pass;
    private String phone;
    private int age;
    private Date createtime;
    private Date updatetime;
    private int astatus;
    private String adesc;

//    //角色信息展示;一个用户有多个角色
//    private List<RoleInfo> roles;
//
    //条件查询使用的
    private String start;
    private String end;
//
//    //这个属性就是用来分页的
//    private PageInfo pg;

    public AdminInfo() {
    }

    public AdminInfo(String pass, String phone, int age) {
        this.pass = pass;
        this.phone = phone;
        this.age = age;
    }

    public AdminInfo(String acount, String pass, String phone, int age) {
        this.acount = acount;
        this.pass = pass;
        this.phone = phone;
        this.age = age;
    }

//    public PageInfo getPg() {
//        return pg;
//    }
//
//    public void setPg(PageInfo pg) {
//        this.pg = pg;
//    }
//
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getAstatus() {
        return astatus;
    }

    public void setAstatus(int astatus) {
        this.astatus = astatus;
    }

    public String getAdesc() {
        return adesc;
    }

    public void setAdesc(String adesc) {
        this.adesc = adesc;
    }

//    public List<RoleInfo> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<RoleInfo> roles) {
//        this.roles = roles;
//    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "id=" + id +
                ", acount='" + acount + '\'' +
                ", pass='" + pass + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", astatus=" + astatus +
                ", adesc='" + adesc + '\'' +
                '}';
    }
}
