package com.example.app50001;

import java.util.HashMap;

public class Boxes {

    public HashMap<String, String> AdminAccess;
    public HashMap<String, String> GuestAccess;
    public HashMap<String, String> DeliveryAccess;

    public Boxes(){}

    public Boxes(HashMap<String,String> adminAccess, HashMap<String, String> guestAccess, HashMap<String, String> deliveryAccess){
        this.AdminAccess = adminAccess;
        this.GuestAccess = guestAccess;
        this.DeliveryAccess = deliveryAccess;
    }
}



//Stores Hashmap of <Unique ID of profile, email of profile
