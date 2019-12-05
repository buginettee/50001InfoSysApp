package com.example.app50001;

import com.google.firebase.auth.AdditionalUserInfo;

import java.util.HashMap;

public class Boxes {

    private HashMap<String, Object> AdminAccess;
    private HashMap<String, Object> GuestAccess;
    private HashMap<String, Object> DeliveryAccess;
    private String Address;
    private String LockState;
    private String DoorState;
    private String ButtonState;
    private String AdditionalInstructions;




    public Boxes(HashMap<String, Object> AdminAccess,
                 HashMap<String, Object> GuestAccess,
                 HashMap<String, Object> DeliveryAccess,
                 String Address,
                 String LockState,
                 String DoorState,
                 String ButtonState,
                 String AdditionalInstructions
                 ){

        this.AdminAccess = AdminAccess;
        this.GuestAccess = GuestAccess;
        this.DeliveryAccess = DeliveryAccess;
        this.Address = Address;
        this.LockState = LockState;
        this.DoorState = DoorState;
        this.ButtonState = ButtonState;
        this.AdditionalInstructions = AdditionalInstructions;
    }
}


