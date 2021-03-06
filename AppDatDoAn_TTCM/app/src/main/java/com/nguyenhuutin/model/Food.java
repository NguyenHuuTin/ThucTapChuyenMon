package com.nguyenhuutin.model;

import java.io.Serializable;

public class Food implements Serializable {
    public int id;
    public String fooodName;
    public int Price;
    public String imgFood;
    public int typeOfFood;
    private boolean isAddToCart;

    public Food() {
    }

    public Food(int id, String fooodName, int price, String imgFood, int typeOfFood) {
        this.id = id;
        this.fooodName = fooodName;
        this.Price = price;
        this.imgFood = imgFood;
        this.typeOfFood = typeOfFood;
    }

    public int getId() {
        return id;
    }

    public String getFooodName() {
        return fooodName;
    }

    public int getPrice() {
        return Price;
    }

    public String getImgFood() {
        return imgFood;
    }

    public int getTypeOfFood() {
        return typeOfFood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFooodName(String fooodName) {
        this.fooodName = fooodName;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setImgFood(String imgFood) {
        this.imgFood = imgFood;
    }

    public void setTypeOfFood(int typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public boolean isAddToCart() {
        return isAddToCart;
    }

    public void setAddToCart(boolean addToCart) {
        isAddToCart = addToCart;
    }
}
