package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
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

public class UserAddGuestsFragment extends Fragment {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    private Button add_new_guests;
    private Button remove_guests;

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    private String currentUID;

    private TextView test_text;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instance of the current user
        dbauth = FirebaseAuth.getInstance();

        FirebaseUser currentuser = dbauth.getCurrentUser();
        currentUID = currentuser.getUid();

        dbreference = FirebaseDatabase.getInstance().getReference();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_add_guests, null);

        add_new_guests = (Button) view.findViewById(R.id.add_new_guests);
        remove_guests = (Button) view.findViewById(R.id.remove_guests);


        add_new_guests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGuestsFragment add_guest_fragment = new AddGuestsFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container, add_guest_fragment);
                trans.commit();
            }
        });

        remove_guests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveGuestsFragment remove_guest_fragment = new RemoveGuestsFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container, remove_guest_fragment);
                trans.commit();
            }
        });



        return view;
    }



    public void preparedata(){

        expandableListTitle = new ArrayList<>();
        expandableListDetail = new HashMap<>();

        test_text.setText("");
        dbreference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot checkadmin : dataSnapshot.child("Profiles").child(currentUID).child("adminOf").getChildren()){

                    String adminbox = checkadmin.getKey().toString();
                    test_text.append(adminbox + "\n");
                    expandableListTitle.add(adminbox);
                    List<String> checkguests = new ArrayList<>();

                    for(DataSnapshot boxguests : dataSnapshot.child("Boxes").child(adminbox).child("guestAccess").getChildren()){
                        checkguests.add(boxguests.getKey().toString() + ": " + boxguests.getValue().toString());
                        test_text.append(boxguests + "\n");
                    }
                    expandableListDetail.put(adminbox,checkguests);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        test_text = (TextView) view.findViewById(R.id.test_text);

        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_guest_list);
        preparedata();
        expandableListAdapter = new backendCustomExpandabaleListView(getActivity(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }
}

//COMPLETED


