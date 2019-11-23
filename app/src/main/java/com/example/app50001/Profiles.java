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
    public Profiles(String DisplayName,
                    String Email,
                    String Address,
                    String AdditionalInformation,
                    String DUID,
                    String Company,
                    HashMap<String, Object> AdminOf,
                    HashMap<String, Object> GuestOf,
                    HashMap<String, Object> DeliveryOf,
                    HashMap<String, Object> AccessHistoryOfProfile,
                    HashMap<String, Object> DeliveryHistoryOfProfile){

        setDisplayName(DisplayName);
        this.Email = Email;
        setAddress(Address);
        setAdditionalInformation(AdditionalInformation);
        this.DUID = DUID;
        this.Company = Company;
        setAdminOf(AdminOf);
        setGuestOf(GuestOf);
        this.DeliveryOf = DeliveryOf;
        this.UserHistoryOfProfile = AccessHistoryOfProfile;
        this.DeliveryHistoryOfProfile = DeliveryHistoryOfProfile;
    }

    //user profile creation
    public Profiles (String DisplayName, String Email, String Address, String AdditionalInformation,
                     HashMap<String, Object> AdminOf, HashMap<String, Object> GuestOf){
        setDisplayName(DisplayName);
        this.Email = Email;
        setAddress(Address);
        setAdditionalInformation(AdditionalInformation);
        setAdminOf(AdminOf);
        setGuestOf(GuestOf);
    }

    //delivery profile creation
    public Profiles (String DisplayName, String Email, String DUID, String Company, HashMap<String, Object> DeliveryOf){
        setDisplayName(DisplayName);
        this.Email = Email;
        this.DUID = DUID;
        this.Company = Company;
        this.DeliveryOf = DeliveryOf;
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



