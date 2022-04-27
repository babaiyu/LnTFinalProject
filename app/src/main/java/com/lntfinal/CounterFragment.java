package com.lntfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CounterFragment extends Fragment {
    static final String COUNTER = "COUNTER";
    Integer counter = 0;
    TextView idCounter;
    Button btnLess, btnPlus, btnReset;
    SharedPreferences storage;
    SharedPreferences.Editor editor;

    public CounterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_counter, container, false);
        idCounter = v.findViewById(R.id.idCounter);
        btnLess = v.findViewById(R.id.btnLess);
        btnPlus = v.findViewById(R.id.btnPluss);
        btnReset = v.findViewById(R.id.btnReset);

        storage = this.getActivity().getSharedPreferences(COUNTER, Context.MODE_PRIVATE);

        updateUI();

        btnLess.setOnClickListener(view -> {
            if (counter > 0) counter--;
            setCounter();
        });

        btnPlus.setOnClickListener(view -> {
            counter++;
            setCounter();
        });

        btnReset.setOnClickListener(view -> {
            counter = 0;
            setCounter();
        });

        return v;
    }

    public void updateUI() {
        counter = storage.getInt("counter", 0);
        idCounter.setText("" + counter);
    }

    public void setCounter() {
        editor = storage.edit();
        editor.putInt("counter", counter);
        editor.apply();

        updateUI();
    }
}