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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserHomeFragment extends Fragment {

    private DatabaseReference dbreference;
    private FirebaseAuth dbauth;

    private TextView admin_access;
    private TextView guest_access;
    private ImageButton forcelock;
    private ImageButton tempunlock;

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

        admin_access = (TextView) view.findViewById(R.id.admin_access_display);
        guest_access = (TextView) view.findViewById(R.id.guest_access_display);
        forcelock = (ImageButton) view.findViewById(R.id.force_lock);
        tempunlock = (ImageButton) view.findViewById(R.id.temp_unlock);

        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                admin_access.setText("");
                guest_access.setText("");

                for(DataSnapshot snapshot : dataSnapshot.child("AdminOf").getChildren()){
                    String admin = snapshot.getValue().toString();
                    admin_access.append(admin);
                    admin_access.append("\n");
                }
                for(DataSnapshot snapshot : dataSnapshot.child("GuestOf").getChildren()){
                    String guest = snapshot.getValue().toString();
                    guest_access.append(guest);
                    guest_access.append("\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        forcelock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForceLockFragment force_lock_now = new ForceLockFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction().replace(R.id.user_fragment_container,force_lock_now);
                trans.commit();
            }
        });



        return view;
    }
}
