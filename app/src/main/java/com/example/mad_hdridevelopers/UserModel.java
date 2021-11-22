package com.example.mad_hdridevelopers;

public class UserModel {

    String location;
    String description;
    String image;

    public UserModel() {
    }

    public UserModel(String location, String description, String image) {
        this.location = location;
        this.description = description;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
