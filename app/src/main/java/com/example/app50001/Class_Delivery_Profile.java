package com.example.app50001;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Class_Delivery_Profile {

    private String name;
    private String DUID; //id of the delivery guy
    private String company_name;


    public Class_Delivery_Profile(){};

    public Class_Delivery_Profile(String name, String DUID, String company_name){
        setName(name);
        this.company_name = company_name;
        this.DUID = DUID;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDUID(){
        return this.DUID;
    }


    public String getCompany_name(){
        return this.company_name;
    }

}



