package com.example.app50001;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeliveryHistoryFragment extends Fragment {

    private DatabaseReference userreference;
    private FirebaseAuth dbauth;

    private List<String> deliverydates;
    private HashMap<String,List<String>> deliverydetails;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TextView invisiblewoman;
    private String currentUID;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get auth from firebase
        dbauth = FirebaseAuth.getInstance();

        //get the current user and its unique ID
        FirebaseUser currentuser = dbauth.getCurrentUser();
        currentUID = currentuser.getUid();

        //get instance of the database
        userreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(currentUID);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery_history, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invisiblewoman = (TextView) view.findViewById(R.id.invisible_woman);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_delivery_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getdeliveries();
        adapter = new backendHistoryCustomRecyclerView(getContext(), deliverydates,deliverydetails);
        recyclerView.setAdapter(adapter);



    }

    public void getdeliveries(){
        deliverydates = new ArrayList<String>();
        deliverydetails = new HashMap<String, List<String>>();


        userreference.child("deliveryHistoryOfProfile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnap : dataSnapshot.getChildren()){

                    String dates = datasnap.getKey().toString();

                    deliverydates.add(dates);

                    for(DataSnapshot snap : dataSnapshot.child(dates).getChildren()){

                        String box = snap.getKey().toString();
                        String address = snap.getValue().toString();

                        List<String> detaillist = new ArrayList<>();
                        detaillist.add(box + "\n" + address);
                        deliverydetails.put(dates, detaillist);

                    }
                    invisiblewoman.setText(dates);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
//COMPLETED



