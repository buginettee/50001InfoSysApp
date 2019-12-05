package com.example.app50001;

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
    private String Contact;

    //no args constructor with setting via get and set methods
    public Boxes(){}


    //get and set methods for every attribute

    public HashMap<String, Object> getAdminAccess() { return AdminAccess; }
    public void setAdminAccess(HashMap<String, Object> AdminAccess) { this.AdminAccess = AdminAccess;  }


    public HashMap<String, Object> getGuestAccess() { return GuestAccess; }
    public void setGuestAccess(HashMap<String, Object> GuestAccess) { this.GuestAccess = GuestAccess; }


    public HashMap<String, Object> getDeliveryAccess() { return DeliveryAccess; }
    public void setDeliveryAccess(HashMap<String, Object> DeliveryAccess) { this.DeliveryAccess = DeliveryAccess; }


    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address = Address; }


    public String getLockState() { return LockState; }
    public void setLockState(String LockState) { this.LockState = LockState; }


    public String getDoorState() { return DoorState; }
    public void setDoorState(String DoorState) { this.DoorState = DoorState;}


    public String getButtonState() {return ButtonState; }
    public void setButtonState(String ButtonState) { this.ButtonState = ButtonState; }


    public String getAdditionalInstructions() { return AdditionalInstructions;}
    public void setAdditionalInstructions(String AdditionalInstructions) { this.AdditionalInstructions = AdditionalInstructions;}


    public String getContact() { return Contact; }
    public void setContact(String Contact) { this.Contact = Contact;}
}

//COMPLETED
