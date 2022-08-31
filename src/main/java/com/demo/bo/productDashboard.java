package com.demo.bo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


import java.io.Serializable;

@Data
public class productDashboard  {
    @Value("#{target.count(id)}")
    private long nbrClient;
    @Value("#{target.sum(quantite)}")
    private long amount;
    @Value("#{target.name}")
    private String nameProduct;
    @Value("#{target.nomCategorie}")
    private String nameCategorie;
    @Value("#{target.price}")
    private float ProductPrice;


    public productDashboard(long nbrClient,long amount ,String nameProduct,String nameCategorie,float ProductPrice ) {
        this.nbrClient = nbrClient;
        this.amount=amount;
        this.nameProduct=nameProduct;
        this.nameCategorie=nameCategorie;
        this.ProductPrice=ProductPrice;
    }

    public String getNameCategorie() {
        return nameCategorie;
    }

    public void setNameCategorie(String nameCategorie) {
        this.nameCategorie = nameCategorie;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    @Override
    public String toString() {
        return "productDashboard{" +
                "nbrClient=" + nbrClient +
                ", amount=" + amount +
                ", nameProduct='" + nameProduct + '\'' +
                ", nameCategorie='" + nameCategorie + '\'' +
                ", ProductPrice=" + ProductPrice +
                '}';
    }

    public float getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(float productPrice) {
        ProductPrice = productPrice;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getNbrClient() {
        return nbrClient;
    }

    public void setNbrClient(long nbrClient) {
        this.nbrClient = nbrClient;
    }
}
