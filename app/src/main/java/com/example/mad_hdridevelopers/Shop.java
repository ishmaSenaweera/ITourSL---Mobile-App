package com.example.mad_hdridevelopers;

public class Shop {

    String s_name, s_description, s_address, s_contactno, s_location, s_pageurl, s_image, s_username, s_password;

    public Shop(){

    }

    public Shop(String s_name, String s_description, String s_address, String s_contactno, String s_location, String s_pageurl, String s_image, String s_username, String s_password) {
        this.s_name = s_name;
        this.s_description = s_description;
        this.s_address = s_address;
        this.s_contactno = s_contactno;
        this.s_location = s_location;
        this.s_pageurl = s_pageurl;
        this.s_image = s_image;
        this.s_username = s_username;
        this.s_password = s_password;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_description() {
        return s_description;
    }

    public void setS_description(String s_description) {
        this.s_description = s_description;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_contactno() {
        return s_contactno;
    }

    public void setS_contactno(String s_contactno) {
        this.s_contactno = s_contactno;
    }

    public String getS_location() {
        return s_location;
    }

    public void setS_location(String s_location) {
        this.s_location = s_location;
    }

    public String getS_pageurl() {
        return s_pageurl;
    }

    public void setS_pageurl(String s_pageurl) {
        this.s_pageurl = s_pageurl;
    }

    public String getS_image() {
        return s_image;
    }

    public void setS_image(String s_image) {
        this.s_image = s_image;
    }

    public String getS_username() {
        return s_username;
    }

    public void setS_username(String s_username) {
        this.s_username = s_username;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }
}