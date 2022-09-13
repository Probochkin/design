package ru.job4j.lsp.storage;

import java.time.LocalDate;
import java.util.Date;

public class Food {
   private  String name;
    private  Date expiryDate;
    private  Date createDate ;
    private   Double price;
    private   int discount;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    public Food(String name, Date expiryDate, Date createDate, Double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public int shellLife() {

       int totalDay = (int) (this.expiryDate.getTime() -  this.createDate.getTime()) / (1000 * 60 * 60 * 24);
       int lastDay = (int)(new Date().getTime() - this.createDate.getTime()) / (1000 * 60 * 60 * 24);

       return  lastDay * 100 / totalDay;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Date getExpiryDate() {
        return expiryDate;
    }

    public  void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public  Date getCreateDate() {
        return createDate;
    }

    public  void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public  Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public  int getDiscount() {
        return discount;
    }

    public  void setDiscount(int discount) {
        this.discount = discount;
    }
}
