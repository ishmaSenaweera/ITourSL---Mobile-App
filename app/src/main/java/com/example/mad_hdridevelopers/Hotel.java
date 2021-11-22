package com.example.mad_hdridevelopers;

public class Hotel {
    private String hotelName;
    private String hotelDescription;
    private String hotelAddress;
    private String hotelContactNo;
    private String hotelLocation;
    private String hotelUrl;
    private String hotelImage;
    private String hotelUsername;
    private String hotelPassword;


    public Hotel() {

    }

    public Hotel(String hotelName, String hotelDescription, String hotelAddress, String hotelContactNo, String hotelLocation, String hotelUrl, String hotelImage, String hotelUsername, String hotelPassword) {
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.hotelAddress = hotelAddress;
        this.hotelContactNo = hotelContactNo;
        this.hotelLocation = hotelLocation;
        this.hotelUrl = hotelUrl;
        this.hotelImage = hotelImage;
        this.hotelUsername = hotelUsername;
        this.hotelPassword = hotelPassword;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelContactNo() {
        return hotelContactNo;
    }

    public void setHotelContactNo(String hotelContactNo) {
        this.hotelContactNo = hotelContactNo;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public String getHotelUrl() {
        return hotelUrl;
    }

    public void setHotelUrl(String hotelUrl) {
        this.hotelUrl = hotelUrl;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelUsername() {
        return hotelUsername;
    }

    public void setHotelUsername(String hotelUsername) {
        this.hotelUsername = hotelUsername;
    }

    public String getHotelPassword() {
        return hotelPassword;
    }

    public void setHotelPassword(String hotelPassword) {
        this.hotelPassword = hotelPassword;
    }
}
