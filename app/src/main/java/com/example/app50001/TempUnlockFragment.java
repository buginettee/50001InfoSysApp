package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TempUnlockFragment extends Fragment {

    private EditText box_id;
    private Button save_changes;

    private DatabaseReference boxRef;
    private DatabaseReference userRef;
    private FirebaseAuth dbauth;
    private DatabaseReference dbreference;

    private String uid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //call on the instances
        dbauth = FirebaseAuth.getInstance();

        dbreference = FirebaseDatabase.getInstance().getReference();

        boxRef = FirebaseDatabase.getInstance().getReference().child("Boxes");
        userRef = FirebaseDatabase.getInstance().getReference().child("Profiles");

        FirebaseUser user = dbauth.getCurrentUser();
        uid = user.getUid();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_temp_unlock, container, false);

        box_id = (EditText) view.findViewById(R.id.to_unlock_display);
        save_changes = (Button) view.findViewById(R.id.save_unlock_changes);

        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //only unlock if the current user in the home login is either admin or guest
                //check for admin status first

                Query queryadmin = boxRef.child(box_id.getText().toString()).child("adminAccess").orderByValue().equalTo(uid);
                queryadmin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            //the user is the admin access

                            boxRef.child(box_id.getText().toString()).child("buttonState").setValue("Unlocked");

                            dbreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    LocalDate date = java.time.LocalDate.now();
                                    String datenow = date.toString();

                                    String box = box_id.getText().toString();
                                    String address = dataSnapshot.child("Boxes").child(box_id.getText().toString()).child("address").getValue().toString();
                                    HashMap<String, Object> useraccessed = new HashMap<>();
                                    useraccessed.put(box, address);


                                    userRef.child(uid).child("userHistoryOfProfile").child(datenow).updateChildren(useraccessed);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });

                            Toast.makeText(getContext(),"You have successfully unlocked the box.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //check if user is on the guestlist
                            Query queryguest = boxRef.child(box_id.getText().toString()).child("guestAccess").orderByValue().equalTo(uid);
                            queryguest.addListenerForSingleValueEvent(new ValueEventListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()){
                                        //the user is a guest
                                        boxRef.child(box_id.getText().toString()).child("buttonState").setValue("Unlocked");

                                        dbreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                LocalDate date = java.time.LocalDate.now();
                                                String datenow = date.toString();

                                                String box = box_id.getText().toString();
                                                String address = dataSnapshot.child("Boxes").child(box_id.getText().toString()).child("address").getValue().toString();

                                                HashMap<String, Object> useraccessed = new HashMap<>();
                                                useraccessed.put(box, address);

                                                userRef.child(uid).child("userHistoryOfProfile").child(datenow).updateChildren(useraccessed);

                                                Toast.makeText(getContext(), "You have successfully unlocked the box.", Toast.LENGTH_SHORT).show();


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

                                    } else {
                                        //since the user is neither guest nor admin, no access is granted
                                        Toast.makeText(getContext(),"You do not have access to this box.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;
    }
}

//COMPLETED