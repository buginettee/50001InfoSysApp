package com.example.app50001;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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

public class UserHomeFragment extends Fragment {

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    private ListView admin_access;
    private ListView guest_access;
    private ImageButton forcelock;
    private ImageButton tempunlock;
    private Button registernewbox;

    private TextView insiviblecandy;

    private List<String> adminaccess;
    private List<String> guestaccess;

    private String uid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get instances
        dbauth = FirebaseAuth.getInstance();

        FirebaseUser user = dbauth.getCurrentUser();
        uid = user.getUid();

        dbreference = FirebaseDatabase.getInstance().getReference().child("Profiles").child(uid);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        admin_access = (ListView) view.findViewById(R.id.admin_access_display);
        guest_access = (ListView) view.findViewById(R.id.guest_access_display);
        forcelock = (ImageButton) view.findViewById(R.id.force_lock);
        tempunlock = (ImageButton) view.findViewById(R.id.temp_unlock);
        registernewbox = (Button) view.findViewById(R.id.register_admin_box);

        insiviblecandy = (TextView) view.findViewById(R.id.invisible_candy);

        getaccesses();

        ArrayAdapter<String> adminarray = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, adminaccess);
        ArrayAdapter<String> guestarray = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, guestaccess);

        admin_access.setAdapter(adminarray);
        guest_access.setAdapter(guestarray);



        forcelock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForceLockFragment force_lock_now = new ForceLockFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container,force_lock_now);
                trans.commit();
            }
        });

        tempunlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TempUnlockFragment temp_unlock_now = new TempUnlockFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container, temp_unlock_now);
                trans.commit();
            }
        });

        registernewbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterAdminBoxFragment registernow = new RegisterAdminBoxFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container, registernow);
                trans.commit();

            }
        });



        return view;
    }

    public void getaccesses(){
        adminaccess = new ArrayList<>();
        guestaccess = new ArrayList<>();

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snap : dataSnapshot.child("adminOf").getChildren()){
                    String boxid = snap.getKey().toString();
                    String address = snap.getValue().toString();

                    adminaccess.add(boxid + "\n" + address);
                    insiviblecandy.setText(boxid);
                }

                for(DataSnapshot snapsnap : dataSnapshot.child("guestOf").getChildren()){
                    String boxid = snapsnap.getKey().toString();
                    String address = snapsnap.getValue().toString();

                    guestaccess.add(boxid + "\n" + address);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
