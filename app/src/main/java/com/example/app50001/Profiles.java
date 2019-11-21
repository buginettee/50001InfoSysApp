package com.example.app50001;

import java.util.HashMap;

//all profile types will be the same
//some attributes will be empty depending who the user is

public class Profiles {

    private String DisplayName; //editable
    public String Email;
    private String Address;     //editable
    private String AdditionalInformation;       //editable
    public String DUID;
    public String Company;
    private HashMap<String, Object> AdminOf;        //editable
    private HashMap<String, Object> GuestOf;        //editable
    public HashMap<String, Object> DeliveryOf;
    public HashMap<String, Object> UserHistoryOfProfile;
    public HashMap<String, Object> DeliveryHistoryOfProfile;

    //no arg constructor
    public Profiles() {}

    //args constructor
    public Profiles(String displayName, String email, String address, String additionalInformation, String duid,
                    String company, HashMap<String, Object> adminOf, HashMap<String, Object> guestOf,
                    HashMap<String, Object> deliveryOf, HashMap<String, Object> accessHistoryOfProfile,
                    HashMap<String, Object> deliveryHistoryOfProfile){

        setDisplayName(displayName);
        this.Email = email;
        setAddress(address);
        setAdditionalInformation(additionalInformation);
        this.DUID = duid;
        this.Company = company;
        setAdminOf(adminOf);
        setGuestOf(guestOf);
        this.DeliveryOf = deliveryOf;
        this.UserHistoryOfProfile = accessHistoryOfProfile;
        this.DeliveryHistoryOfProfile = deliveryHistoryOfProfile;
    }

    //user profile creation
    public Profiles (String displayName, String email, String address, String additionalInformation,
                     HashMap<String, Object> adminOf, HashMap<String, Object> guestOf){
        setDisplayName(displayName);
        this.Email = email;
        setAddress(address);
        setAdditionalInformation(additionalInformation);
        setAdminOf(adminOf);
        setGuestOf(guestOf);
    }

    //delivery profile creation
    public Profiles (String DisplayName, String email, String duid, String company, HashMap<String, Object> deliveryOf){
        setDisplayName(DisplayName);
        this.Email = email;
        this.DUID = duid;
        this.Company = company;
        this.DeliveryOf = deliveryOf;
    }




    public String getDisplayName(){return this.DisplayName;}
    public void setDisplayName(String displayName){ this.DisplayName = displayName; }


    public String getAddress(){ return this.Address; }
    public void setAddress(String address ) { this.Address = address; }


    public String getAdditionalInformation() {return this.AdditionalInformation;}
    public void setAdditionalInformation(String additionalInformation) {this.AdditionalInformation = additionalInformation;}


    public HashMap<String, Object> getAdminOf(){ return this.AdminOf; }
    public void setAdminOf(HashMap<String, Object> adminOf) { this.AdminOf = adminOf; }


    public HashMap<String, Object> getGuestOf(){ return this.GuestOf; }
    public void setGuestOf(HashMap<String, Object> guestOf) { this.GuestOf = guestOf; }


}



