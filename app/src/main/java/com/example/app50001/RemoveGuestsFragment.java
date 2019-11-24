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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RemoveGuestsFragment extends Fragment {

    private EditText remove_guest_UID;
    private EditText remove_box;
    private Button save_all_settings;

    private DatabaseReference userRef;
    private DatabaseReference boxRef;
    private FirebaseAuth dbauth;

    private String UID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbauth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = dbauth.getCurrentUser();
        UID = currentuser.getUid();

        userRef = FirebaseDatabase.getInstance().getReference().child("Profiles");

        boxRef = FirebaseDatabase.getInstance().getReference().child("Boxes");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_remove_guests, container, false);

        remove_guest_UID = (EditText) view.findViewById(R.id.remove_guest_UID_display);
        remove_box = (EditText) view.findViewById(R.id.remove_guest_box_display);
        save_all_settings = (Button) view.findViewById(R.id.save_remove_guest_changes);

        save_all_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!remove_guest_UID.getText().toString().isEmpty() ||
                    !remove_box.getText().toString().isEmpty()){

                    //action legal if i am actually admin
                    Query query = boxRef.child(remove_box.getText().toString()).child("AdminAccess").orderByValue().equalTo(UID);

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){

                                //check if the guest is actually in the guest list
                                Query query2 = boxRef.child(remove_box.getText().toString()).child("GuestAccess").orderByValue().equalTo(remove_guest_UID.getText().toString());

                                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if(dataSnapshot.exists()){

                                            //remove from the box guest access
                                            dataSnapshot.getRef().removeValue();

                                            userRef.child("GuestOf").child(remove_box.getText().toString()).removeValue();


                                        } else {
                                            Toast.makeText(getContext(),"Guest does not exist", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });

                            } else {
                                Toast.makeText(getContext(),"You do not have admin access", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        return view;
    }
}

//DONE
//EVERYTHING WORKS
