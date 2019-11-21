package com.example.app50001;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.app50001.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ObjectStreamException;
import java.util.HashMap;
import java.util.Iterator;

public class DeliveryHomeFragment extends Fragment {

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;
    private Button DeliveriesToMake;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get auth from firebase
        dbauth = FirebaseAuth.getInstance();

        //get the current user and its unique ID
        FirebaseUser currentuser = dbauth.getCurrentUser();
        String currentUID = currentuser.getUid();

        //get instance of the database
        dbreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(currentUID);

        //if you want to add more things under the same children, use a hashmap and .updateChildren method
        //FOR YOUR REFERENCE
        // HashMap<String, Object> newdelivery = new HashMap<>();
        //newdelivery.put("box5", "blk 61");
        //dbreference.child("DeliveryOf").updateChildren(newdelivery);
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery_home, null);

        DeliveriesToMake = (Button) view.findViewById(R.id.delivery1);

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //text in the button has to be set empty first to prevent appending problems
                DeliveriesToMake.setText("");


                //iterator method if you need to iterate through the multiple keys inside the DeliveryOf child
                for (DataSnapshot snapshot : dataSnapshot.child("DeliveryOf").getChildren()){

                    String string = snapshot.getKey().toString();
                    DeliveriesToMake.append(string);
                    DeliveriesToMake.append("\n");
                    String value = snapshot.getValue().toString();
                    DeliveriesToMake.append(value);
                    DeliveriesToMake.append("\n");

                }

                //as this is referring to a user profile, retrieve the info from profiles -> deliveryof
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }
}



//HI if you need any references, use DeliverySettingsFragment as your code reference thanks!

//The above space is for you to do your code
//onCreate for all the variables and reference IDs that you need inside the page
//onCreateView for all the actual action going on when the person clicks on the page
//i added one iterator method for you in case you need to iterate through the different nodes inside the DeliveryOf
//method. Also the format for this display for each delivery should be:

//Box #*****
//Address: _______

//idk how you are going to make this but goodluck! HAHHAHAA i tried to help abit but idk what to do already

