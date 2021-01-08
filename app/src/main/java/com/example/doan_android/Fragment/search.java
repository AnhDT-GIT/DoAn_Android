package com.example.doan_android.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_android.Activity.DsBaihat;
import com.example.doan_android.Activity.MainActivity;
import com.example.doan_android.R;


public class search extends Fragment {
    View view;
    public search() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        Button button = view.findViewById(R.id.timkiem);
        EditText editText=view.findViewById(R.id.timkiemtext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DsBaihat.class);
                intent.putExtra("text",editText.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
}
