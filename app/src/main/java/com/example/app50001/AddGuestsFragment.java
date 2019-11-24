package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddGuestsFragment extends Fragment {

    private EditText add_guest_name;
    private EditText add_guest_UID;
    private EditText add_box;
    private Button save_changes;

    private DatabaseReference userRef;
    private DatabaseReference boxRef;
    private FirebaseAuth dbauth;

    private String uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instances to database and auth
        dbauth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = dbauth.getCurrentUser();
        uid = currentuser.getUid();

        userRef = FirebaseDatabase.getInstance().getReference().child("Profiles");

        boxRef = FirebaseDatabase.getInstance().getReference().child("Boxes");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_guests, container,  false);

        add_guest_name = (EditText) view.findViewById(R.id.add_guest_name_display);
        add_guest_UID = (EditText) view.findViewById(R.id.add_guest_UID_display);
        add_box = (EditText) view.findViewById(R.id.add_guest_box_display);
        save_changes = (Button) view.findViewById(R.id.save_add_guest_changes);


        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!add_guest_name.getText().toString().isEmpty() ||
                    !add_guest_UID.getText().toString().isEmpty() ||
                    !add_box.getText().toString().isEmpty()){

                    Query query = boxRef.child(add_box.getText().toString()).child("AdminAccess")
                            .orderByValue().equalTo(uid);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String guest_name = add_guest_name.getText().toString();
                            String guest_UID = add_guest_UID.getText().toString();
                            String box_UID = add_box.getText().toString();

                            if(dataSnapshot.exists()){

                                HashMap<String, Object> newAccess = new HashMap<>();
                                newAccess.put(guest_name,guest_UID);

                                boxRef.child(box_UID).child("GuestAccess").updateChildren(newAccess);


                                HashMap<String,Object> newGuest = new HashMap<>();
                                newGuest.put(box_UID, boxRef.child(box_UID).child("Address").getKey().toString());

                                userRef.child(guest_UID).child("GuestOf").updateChildren(newGuest);

                                Toast.makeText(getContext(), "You have successfully granted guest access.", Toast.LENGTH_SHORT).show();


                                UserAddGuestsFragment add_guest_fragment = new UserAddGuestsFragment();
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container, add_guest_fragment);
                                trans.commit();

                            } else {

                                Toast.makeText(getContext(), "You do not have admin access to complete this.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}

//DONE
//MAYBE ADD LOG
