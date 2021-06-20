package com.nguyenhuutin.model;

public class Order {
    public int id;
    public String time;
    public String address;
    public String total;
    public int tt;

    public Order() {
    }

    public Order(int id, String time, String address, String total, int tt) {
        this.id = id;
        this.time = time;
        this.address = address;
        this.total = total;
        this.tt = tt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }
}
