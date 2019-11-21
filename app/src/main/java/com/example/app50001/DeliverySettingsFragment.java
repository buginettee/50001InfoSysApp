package com.example.app50001;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeliverySettingsFragment extends Fragment {

    private DatabaseReference dbreference;
    private TextView delivery_id;
    private FirebaseAuth dbauth;
    private TextView company_name;
    private Button log_out_button;
    private EditText namechange;
    private Button save_all_settings;
    private String currentuserUID;

    //delivery person details should already be in the database
    //settings to retrieve name, delivery id, company name


    public void onCreate(Bundle savedInstanceState){ //initialising things. methods should be in onCreateView
        super.onCreate(savedInstanceState);
        Log.i("Settings Page Access", "Settings Page Accessed");


        //get user authentication data
        dbauth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = dbauth.getCurrentUser();
        currentuserUID = currentuser.getUid();

        //get instance of firebase access to the specific account details
        dbreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(currentuserUID);

    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //inflate view of the fragment
        View view = inflater.inflate(R.layout.fragment_delivery_settings, null);

        delivery_id = (TextView) view.findViewById(R.id.delivery_id_display);

        company_name = (TextView) view.findViewById(R.id.delivery_company_display);

        namechange = (EditText) view.findViewById(R.id.delivery_edit_name);
        namechange.setHint("");



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        DatabaseReference delivery_profiles = ref.child("Profiles");

        delivery_profiles.child(currentuserUID).setValue(new Class_Profile("name", "address", "extrainfo","duid", "company"));




        //retrieving and displaying data from the database
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //read from the database and display the results
                //need to empty the text bc append will extend the displayed items
                delivery_id.setText("");
                company_name.setText("");
                namechange.setText("");

                //to retrieve, please look at the database for the specific child name
                delivery_id.append(dataSnapshot.child("deliveryID").getValue().toString());
                company_name.append(dataSnapshot.child("company_Name").getValue().toString());
                namechange.append(dataSnapshot.child("displayName").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.i("Fetch Profile Failure", "Fetching Delivery Profile Failure");
            }
        });

        log_out_button = (Button) view.findViewById(R.id.delivery_logout);
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbauth.signOut();

                Toast.makeText(getContext(), "You have logged out", Toast.LENGTH_SHORT).show();

                Log.i("Logout", "Successful logout");
                Intent logouttomain = new Intent(getActivity(), Login_Main.class);
                startActivity(logouttomain);
            }
        });



        save_all_settings = (Button) view.findViewById(R.id.save_settings);
        save_all_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbreference.child("name").setValue(namechange.getText().toString());

                Toast.makeText(getContext(), "You have successfully updated your name", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}


//DONE
//EVERYTHING WORKS


//TODO LOG EVERYTHING
//TODO REDUCE RETRIEVAL TIME
//TODO FOR FUTURE: ADD PROFILE PICTURE



