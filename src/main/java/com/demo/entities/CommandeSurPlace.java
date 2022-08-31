package com.demo.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommandeSurPlace {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     private String productName;
     private int Quantity;
     private float prixTotal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date drive;

    @Temporal(TemporalType.TIME)
    private Date heurRamassage;

    public Date getHeurRamassage() {
        return heurRamassage;
    }

    public void setHeurRamassage(Date heurRamassage) {
        this.heurRamassage = heurRamassage;
    }

    public Date getDrive() {
        return drive;
    }

    public void setDrive(Date drive) {
        this.drive = drive;
    }

    public CommandeSurPlace() {

    }

    public int getQuantity() {
        return Quantity;
    }

    @Override
    public String toString() {
        return "CommandeSurPlace{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", Quantity=" + Quantity +
                ", prixTotal=" + prixTotal +
                '}';
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommandeSurPlace(String productName, int Quantity, float prixTotal,Date drive, Date heurRamassage){
         this.productName=productName;
         this.Quantity=Quantity;
         this.prixTotal=prixTotal;
         this.drive=drive;
         this.heurRamassage=heurRamassage;
     }

}
