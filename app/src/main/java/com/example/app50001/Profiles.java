package com.example.app50001;

import java.util.HashMap;

//all profile types will be the same
//some attributes will be empty depending who the user is

public class Profiles {

    private String DisplayName; //editable
    private String Email;
    private String DUID;
    private String Company;
    private HashMap<String, Object> AdminOf;        //editable
    private HashMap<String, Object> GuestOf;        //editable
    private HashMap<String, Object> DeliveryOf;
    private HashMap<String, Object> UserHistoryOfProfile;
    private HashMap<String, Object> DeliveryHistoryOfProfile;

    //no arg constructor
    public Profiles() {}

    //all getter and setter methods
    public String getDisplayName() { return DisplayName; }
    public void setDisplayName(String DisplayName) { this.DisplayName = DisplayName; }


    public String getEmail() { return Email; }
    public void setEmail(String Email) {  this.Email = Email;  }


    public String getDUID() {  return DUID; }
    public void setDUID(String DUID) { this.DUID = DUID; }


    public String getCompany() {  return Company; }
    public void setCompany(String Company) {  this.Company = Company; }


    public HashMap<String, Object> getAdminOf() { return AdminOf;  }
    public void setAdminOf(HashMap<String, Object> AdminOf) {  this.AdminOf = AdminOf; }


    public HashMap<String, Object> getGuestOf() {return GuestOf;}
    public void setGuestOf(HashMap<String, Object> GuestOf) { this.GuestOf = GuestOf;}


    public HashMap<String, Object> getDeliveryOf() { return DeliveryOf; }
    public void setDeliveryOf(HashMap<String, Object> DeliveryOf) {this.DeliveryOf = DeliveryOf; }


    public HashMap<String, Object> getUserHistoryOfProfile() {return UserHistoryOfProfile;}
    public void setUserHistoryOfProfile(HashMap<String, Object> UserHistoryOfProfile) {
        this.UserHistoryOfProfile = UserHistoryOfProfile;
    }

    public HashMap<String, Object> getDeliveryHistoryOfProfile() {
        return DeliveryHistoryOfProfile;
    }

    public void setDeliveryHistoryOfProfile(HashMap<String, Object> deliveryHistoryOfProfile) {
        DeliveryHistoryOfProfile = deliveryHistoryOfProfile;
    }
}

//COMPLETED


