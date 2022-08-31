package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private String details;

    private boolean readNot;

    public notification(String title, String description, String details,boolean read ) {
        this.title=title;
        this.description=description;
        this.details=details;
        this.readNot=read;
    }


    @Override
    public String toString() {
        return "notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                ", read=" + readNot +
                '}';
    }

    public boolean isRead() {
        return readNot;
    }

    public void setRead(boolean read) {
        this.readNot = read;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public notification(long id, String title, String description, String details) {
        this.id =id;
        this.title = title;
        this.description=description;
        this.details=details;
    }

    public notification() {

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
