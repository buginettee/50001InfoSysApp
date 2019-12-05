package com.example.app50001;

import com.google.firebase.auth.AdditionalUserInfo;

import java.util.HashMap;

public class Boxes {

    public HashMap<String, Object> AdminAccess;
    public HashMap<String, Object> GuestAccess;
    public HashMap<String, Object> DeliveryAccess;
    public String Address;
    public boolean LockState;
    public boolean DoorState;
    public String ButtonState;
    public String AdditionalInstructions;

    public Boxes(){}

    public Boxes(HashMap<String, Object> adminAccess,
                 HashMap<String, Object> guestAccess,
                 HashMap<String, Object> deliveryAccess,
                 String Address,
                 Boolean LockState,
                 Boolean DoorState,
                 String ButtonState,
                 String AdditionalInstructions
                 ){

        this.AdminAccess = adminAccess;
        this.GuestAccess = guestAccess;
        this.DeliveryAccess = deliveryAccess;
        this.Address = Address;
        this.LockState = LockState;
        this.DoorState = DoorState;
        this.ButtonState = ButtonState;
        this.AdditionalInstructions = AdditionalInstructions;
    }
}


