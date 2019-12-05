package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class UserHistoryFragment extends Fragment {

    private DatabaseReference userreference;
    private FirebaseAuth dbauth;

    private List<String> userdates;
    private HashMap<String, List<String>> userdetails;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private String uid;
    private TextView invisiblechild;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instances from the database
        dbauth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = dbauth.getCurrentUser();
        uid = currentuser.getUid();

        userreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(uid);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invisiblechild = (TextView) view.findViewById(R.id.invisible_child);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_user_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gethistory();
        adapter = new backendHistoryCustomRecyclerView(getContext(),userdates,userdetails);
        recyclerView.setAdapter(adapter);
    }

    public void gethistory(){

        userdates = new ArrayList<String>();
        userdetails = new HashMap<String, List<String>>();


        userreference.child("userHistoryOfProfile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnap : dataSnapshot.getChildren()){

                    String dates = datasnap.getKey().toString();

                    userdates.add(dates);

                    for(DataSnapshot snap : dataSnapshot.child(dates).getChildren()){

                        String box = snap.getKey().toString();
                        String address = snap.getValue().toString();

                        List<String> detaillist = new ArrayList<>();
                        detaillist.add(box + "\n" + address);
                        userdetails.put(dates, detaillist);
                    }
                    invisiblechild.setText(dates);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
//COMPLETED