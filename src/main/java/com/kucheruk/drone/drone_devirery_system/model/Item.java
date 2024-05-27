package com.kucheruk.drone.drone_devirery_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Table(name = "items")
@Entity
public class Item {
    @NotEmpty
    @Pattern(regexp="^[a-zA-Z0-9_-]*$",message="Allowed only letters, numbers, hyphen and underscore")
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false, scale = 2)
    private double weight;

    @Id
    @Pattern(regexp="^[A-Z0-9_]*$",message="Allowed only upper case letters, underscore and numbers")
    @Column(nullable = false)
    private String code;

    @NotEmpty
    @Column(nullable = false)
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item() {

    }

    public Item(String name, double weight, String code, String image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }
}
