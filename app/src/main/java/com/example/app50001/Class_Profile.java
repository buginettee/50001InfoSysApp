package com.example.app50001;

import java.util.HashMap;
import java.util.HashSet;

public class Class_Profile {

    private String DisplayName;
    private String Address;
    private String AdditionalDeliveryInformation;
    private String DeliveryID;
    private String Company_Name;
    private HashMap<String, HashSet<String>> DeliveryInstructions;


    //this will be the combined profile of any email that signs up
    //the same account can log into either delivery or user
    //but some attributes can only be added in exclusively via the database (companny name and DUID)


    Class_Profile(String name, String address, String extraInfo, String DUID, String company, HashMap<String, HashSet<String>> DeliveryInstructions){
        setDisplayName(name);
        setAddress(address);
        setAdditionalInformation(extraInfo);
        this.DeliveryID = DUID; //exclusive addition via DB
        this.Company_Name = company; //exclusive addition via DB
    }

    public String getDisplayName(){
        return this.DisplayName;
    }
    public void setDisplayName(String displayName) {
        this.DisplayName = displayName;
    }


    public String getAddress(){ return this.Address; }
    public void setAddress(String address){
        this.Address = address;
    }

    public String getAdditionalInformation() {return this.AdditionalDeliveryInformation;}
    public void setAdditionalInformation(String additionalInfo) {
        this.AdditionalDeliveryInformation = additionalInfo;
    }


}
