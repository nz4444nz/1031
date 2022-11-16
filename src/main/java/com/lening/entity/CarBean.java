package com.lening.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CarBean {
    private Integer carid;

    private String carname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buydate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date edate;

    private ColorBean cb = new ColorBean();

    public ColorBean getCb() {
        return cb;
    }

    public void setCb(ColorBean cb) {
        this.cb = cb;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    private String address;

    private Integer cid;

    private String userurl;

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname == null ? null : carname.trim();
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getUserurl() {
        return userurl;
    }

    public void setUserurl(String userurl) {
        this.userurl = userurl == null ? null : userurl.trim();
    }
}