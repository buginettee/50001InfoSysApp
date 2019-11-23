package com.example.app50001;

import java.util.HashMap;

public class Boxes {

    public HashMap<String, Object> AdminAccess;
    public HashMap<String, Object> GuestAccess;
    public HashMap<String, Object> DeliveryAccess;
    public boolean LockState;
    public boolean DoorState;
    public boolean AccessState;

    public Boxes(){}

    public Boxes(HashMap<String, Object> adminAccess,
                 HashMap<String, Object> guestAccess,
                 HashMap<String, Object> deliveryAccess,
                 Boolean LockState,
                 Boolean DoorState,
                 Boolean AccessState){

        this.AdminAccess = adminAccess;
        this.GuestAccess = guestAccess;
        this.DeliveryAccess = deliveryAccess;
        this.LockState = LockState;
        this.DoorState = DoorState;
        this.AccessState = AccessState;
    }
}


