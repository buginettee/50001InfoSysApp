package com.example.app50001;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeliveryHistoryFragment extends Fragment {

    private FirebaseAuth dbauth;
    private DatabaseReference dbreference;
    private TextView deliveryAccessHistory;


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


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery_history, null);

        deliveryAccessHistory = (TextView) view.findViewById(R.id.retrieve_delivery_history);

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                deliveryAccessHistory.setText("");


                //access only the DeliveryHistoryOfProfile
                for(DataSnapshot snapshot : dataSnapshot.child("DeliveryHistoryOfProfile").getChildren()){
                    String date = snapshot.getKey().toString();
                    deliveryAccessHistory.append(date);
                    deliveryAccessHistory.append("\n");
                    for(DataSnapshot snapshotchild : snapshot.getChildren()){
                        String accessedbox = snapshotchild.getKey().toString();
                        deliveryAccessHistory.append(accessedbox);
                        deliveryAccessHistory.append("\n");
                        String accessedaddress = snapshotchild.getValue().toString();
                        deliveryAccessHistory.append(accessedaddress);
                        deliveryAccessHistory.append("\n");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}


//HI i used the same iterator again up there in case you need some form of method iteration
//feel free to delete if the recycler view works without it

//for delivery, it will only access DeliveryHistoryOfProfile (you need to look at the database for the correct name
//if not the database will create a new branch which is fking stupid (delete it if you created extras)


