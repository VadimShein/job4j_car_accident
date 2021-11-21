package ru.job4j.accident.model;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.Objects;

@Component
public class Accident {
    private int id;
    private String address;
    private String carNumber;
    private String description;
    private File photo;
    private String author;
    private Date created;
    private String status;

    public Accident() {
    }

    public Accident(int id, String description, String carNumber, String address, Date created, String author, String status) {
        this.id = id;
        this.description = description;
        this.carNumber = carNumber;
        this.address = address;
        this.created = created;
        this.author = author;
        this.status = status;
    }

    public Accident(String description, String carNumber, String address, Date created, String author, String status) {
        this.description = description;
        this.carNumber = carNumber;
        this.address = address;
        this.created = created;
        this.author = author;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
