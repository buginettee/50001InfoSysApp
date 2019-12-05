package com.example.app50001;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app50001.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DeliveryHomeFragment extends Fragment {

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    private RecyclerView recycler;

    private List<String> boxes;
    private HashMap<String, List<String>> info; //boxes, <address, addinfo>

    private RecyclerView.Adapter adapter;

    private TextView invisible;
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
        dbreference = FirebaseDatabase.getInstance().getReference();

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delivery_home, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        invisible = (TextView) view.findViewById(R.id.invisible_man);

        recycler = (RecyclerView) view.findViewById(R.id.delivery_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        getdeliveries();
        adapter = new backendDeliveryCustomRecyclerView(getContext(),boxes,info,this);
        recycler.setAdapter(adapter);
    }

    public void getdeliveries(){
        boxes = new ArrayList<String>();
        info = new HashMap<String, List<String>>();

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot datasnap : dataSnapshot.child("Profiles").child(currentUID).child("DeliveryOf").getChildren()) {

                    String boxid = datasnap.getKey().toString();
                    String boxaddress = datasnap.getValue().toString();

                    boxes.add(boxid);

                    String boxinfo = dataSnapshot.child("Boxes").child(boxid).child("AdditionalInstructions").getValue().toString();

                    List<String> temp = new ArrayList<>();
                    temp.add(boxaddress);
                    temp.add(boxinfo);
                    info.put(boxid,temp);

                    invisible.setText(boxid);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void completeDelivery(String box){ //given the box that i have chose to complete delivery for,
        //change box to unlocked, remove from deliveryof, remove from deliveryaccess of box

        dbreference.child("Boxes").child(box).child("ButtonState").setValue("Unlocked");
        dbreference.child("Profiles").child(currentUID).child("DeliveryOf").removeValue();
        dbreference.child("Boxes").child(box).child("DeliveryAccess").removeValue();

    }

}



