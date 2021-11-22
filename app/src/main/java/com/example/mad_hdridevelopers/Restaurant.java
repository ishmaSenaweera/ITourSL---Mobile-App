package com.example.mad_hdridevelopers;

public class Restaurant {


    private String restName;
    private String restDescription;
    private String restAddress;
    private String restContactNo;
    private String restLocation;
    private String restUrl;
    private String restImage;
    private String restEmail;
    private String restPassword;
    Restaurant(){
    }
    public Restaurant(String restName, String restDescription,
                      String restAddress, String restContactNo, String restLocation,
                      String restUrl, String restImage, String restEmail, String restPassword) {
        this.restName = restName;
        this.restDescription = restDescription;
        this.restAddress = restAddress;
        this.restContactNo = restContactNo;
        this.restLocation = restLocation;
        this.restUrl = restUrl;
        this.restImage = restImage;
        this.restEmail = restEmail;
        this.restPassword = restPassword;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestDescription() {
        return restDescription;
    }

    public void setRestDescription(String restDescription) {
        this.restDescription = restDescription;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getRestContactNo() {
        return restContactNo;
    }

    public void setRestContactNo(String restContactNo) {
        this.restContactNo = restContactNo;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }

    public String getRestUrl() {
        return restUrl;
    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public String getRestImage() {
        return restImage;
    }

    public void setRestImage(String restImage) {
        this.restImage = restImage;
    }

    public String getRestEmail() {
        return restEmail;
    }

    public void setRestEmail(String restEmail) {
        this.restEmail = restEmail;
    }

    public String getRestPassword() {
        return restPassword;
    }

    public void setRestPassword(String restPassword) {
        this.restPassword = restPassword;
    }

}
