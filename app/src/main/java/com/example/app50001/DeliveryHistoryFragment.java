package com.example.app50001;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.app50001.R;

public class DeliveryHistoryFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_delivery_history, null);
    }
}

//TODO HOW TO RETRIEVE AND DISPLAY FROM THE DATABASE THE HISTORY THAT THIS DELIVERY GUY HAS DELIVERED