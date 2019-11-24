package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class UserSettingsFragment extends Fragment {

    private Button home_logout;
    private EditText name_display;
    private TextView email_display;
    private EditText address_display;
    private EditText additionalinfo_display;
    private Button save_settings;

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instance of the current user
        dbauth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = dbauth.getCurrentUser();
        String currentUID = currentuser.getUid();

        //get instance of the database to access from
        dbreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(currentUID);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_settings,container, false);

        name_display = (EditText) view.findViewById(R.id.user_name_display);
        email_display = (TextView) view.findViewById(R.id.user_email_display);
        address_display = (EditText) view.findViewById(R.id.user_address_display);
        additionalinfo_display = (EditText) view.findViewById(R.id.user_additionalinfo_display);


        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name_display.setText("");
                email_display.setText("");
                address_display.setText("");
                additionalinfo_display.setText("");

                name_display.append(dataSnapshot.child("displayName").getValue().toString());
                email_display.append(dataSnapshot.child("Email").getValue().toString());
                address_display.append(dataSnapshot.child("address").getValue().toString());
                additionalinfo_display.append(dataSnapshot.child("additionalInformation").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        home_logout = (Button) view.findViewById(R.id.home_logout);
        home_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbauth.signOut();

                Toast.makeText(getContext(), "You have successfully logged out.", Toast.LENGTH_SHORT).show();

                Intent loggedout = new Intent(getActivity(), Login_Main.class);
                startActivity(loggedout);
            }
        });


        save_settings = (Button) view.findViewById(R.id.user_save_all_settings);
        save_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbreference.child("displayName").setValue(name_display.getText().toString());

                dbreference.child("address").setValue(address_display.getText().toString());

                dbreference.child("additionalInformation").setValue(additionalinfo_display.getText().toString());

                Toast.makeText(getContext(), "You have successfully updated all settings.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

//DONE
//ALL WORKING
