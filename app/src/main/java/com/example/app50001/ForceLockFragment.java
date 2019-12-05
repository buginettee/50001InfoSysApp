package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

public class ForceLockFragment extends Fragment {

    private DatabaseReference adminRef;
    private DatabaseReference boxRef;
    private FirebaseAuth dbauth;

    private EditText box_id;
    private Button save_changes;

    private String uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //call instances of the database
        dbauth = FirebaseAuth.getInstance();

        FirebaseUser user = dbauth.getCurrentUser();
        uid = user.getUid();

        adminRef = FirebaseDatabase.getInstance().getReference().child("Profiles");
        boxRef = FirebaseDatabase.getInstance().getReference().child("Boxes");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_force_lock, container, false);
        final String check = "locked";

        box_id = (EditText) view.findViewById(R.id.to_force_lock_display);

        save_changes = (Button) view.findViewById(R.id.save_force_lock_changes);
        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //lock only if the current user is the admin of the box input
                Query query = boxRef.child(box_id.getText().toString()).child("AdminAccess").orderByValue().equalTo(uid);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //the currentuser is indeed the admin of the current box
                        if(dataSnapshot.exists()){

                            //i am admin and now to check if my action is legal
                            //checking if the doorstate is currently locked
                            Query query2 = boxRef.child(box_id.getText().toString()).child("LockState").orderByValue().equalTo(check);//locked and unlocked
                            query2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {

                                        Query query3 = boxRef.child(box_id.getText().toString()).child("DoorState").orderByValue().equalTo(check);//locked and unlocked
                                        query3.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {

                                                    boxRef.child(box_id.getText().toString()).child("ButtonState").setValue("Locked");

                                                    Toast.makeText(getContext(), "You have successfully locked the box", Toast.LENGTH_SHORT).show();

                                                } else {
                                                    Toast.makeText(getContext(), "Problem A.", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                            }
                                        });
                                    } else {
                                        Toast.makeText(getContext(), "Problem B.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {}
                            });

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(getContext(),"You do not have admin access to this box", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        return view;
    }
}
