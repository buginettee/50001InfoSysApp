package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterAdminBoxFragment extends Fragment {

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    private EditText boxidinput;
    private EditText addressinput;
    private EditText contactinput;
    private EditText addinfoinput;
    private Button saveregister;

    private String uid;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instances
        dbauth = FirebaseAuth.getInstance();

        dbreference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = dbauth.getCurrentUser();
        uid = user.getUid();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register_admin_box, container, false);

        boxidinput = (EditText) view.findViewById(R.id.enter_boxid_display);
        addressinput = (EditText) view.findViewById(R.id.enter_address_display);
        contactinput = (EditText) view.findViewById(R.id.enter_contact_display);
        addinfoinput = (EditText) view.findViewById(R.id.enter_addinfo_display);
        saveregister = (Button) view.findViewById(R.id.save_register);

        getname();

        saveregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!boxidinput.getText().toString().isEmpty() && !addressinput.getText().toString().isEmpty() &&
                    !contactinput.getText().toString().isEmpty()){
                    //if the important fields are not empty, then create a new branch inside the database with said inputs

                    Boxes newadminbox = new Boxes();
                    newadminbox.setAddress(addressinput.getText().toString());
                    newadminbox.setLockState("locked");
                    newadminbox.setDoorState("locked");
                    newadminbox.setButtonState("Locked");
                    newadminbox.setAdditionalInstructions(addinfoinput.getText().toString());
                    newadminbox.setContact(contactinput.getText().toString());

                    HashMap<String, Object> firstentry = new HashMap<>();
                    firstentry.put("first entry", "first entry");

                    HashMap<String,Object> adminaccess = new HashMap<>();
                    adminaccess.put("first entry", "first entry");
                    adminaccess.put(name, uid);

                    newadminbox.setAdminAccess(adminaccess);
                    newadminbox.setGuestAccess(firstentry);
                    newadminbox.setDeliveryAccess(firstentry);

                    dbreference.child("Boxes").child(boxidinput.getText().toString()).setValue(newadminbox);

                    Toast.makeText(getContext(), "You have successfully registered a new box as admin.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "Registration unsuccessfully. Please ensure that you have entered the Box ID accurately.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
    public void getname(){
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("Profiles").child(uid).child("displayName").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
