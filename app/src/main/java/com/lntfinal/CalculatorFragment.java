package com.lntfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {
    Float rectangle, triangle, circle;
    EditText inputSide, inputBase, inputTall, inputFinger;
    TextView txtRectangle, txtTriangle, txtCircle;
    Button btnArea;

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculator, container, false);
        inputSide = v.findViewById(R.id.inputSide);
        inputBase = v.findViewById(R.id.inputBase);
        inputTall = v.findViewById(R.id.inputTall);
        inputFinger = v.findViewById(R.id.inputFinger);
        txtRectangle = v.findViewById(R.id.txtRectangle);
        txtTriangle = v.findViewById(R.id.txtTriangle);
        txtCircle = v.findViewById(R.id.txtCircle);
        btnArea = v.findViewById(R.id.btnArea);

        btnArea.setOnClickListener(view -> {
            Integer side = Integer.parseInt(inputSide.getText().toString());
            Integer base = Integer.parseInt(inputBase.getText().toString());
            Integer tall = Integer.parseInt(inputTall.getText().toString());
            Integer finger = Integer.parseInt(inputFinger.getText().toString());

            areaRectangle(side);

            areaTriangle(base, tall);

            areaCircle(finger);
        });

        return v;
    }

    public void areaRectangle(Integer side) {
        rectangle = (float) side * side;
        txtRectangle.setText(String.format("Persegi: %s", rectangle));
    }

    public void areaTriangle(Integer base, Integer tall) {
        triangle = (float) 1 / 3 * base * tall;
        txtTriangle.setText(String.format("Segitiga: %s", triangle));
    }

    public void areaCircle(Integer finger) {
        circle = (float) 22 / 7 * finger * finger;
        txtCircle.setText(String.format("Lingkaran: %s", circle));
    }
}