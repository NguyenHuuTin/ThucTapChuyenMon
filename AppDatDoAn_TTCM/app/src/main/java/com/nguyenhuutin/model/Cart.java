package com.nguyenhuutin.model;

public class Cart {
    public int ID;
    public String Name;
    public int Price;
    public String img;
    public int SL;

    public Cart() {
    }

    public Cart(int ID, String name, int price, String img, int SL) {
        this.ID = ID;
        Name = name;
        Price = price;
        this.img = img;
        this.SL = SL;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }
}
