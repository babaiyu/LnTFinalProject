package com.lntfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class VolumeFragment extends Fragment {
    Float block, pyramid, tube;
    EditText inputLength, inputWide, inputTall, inputBaseArea, inputRadius;
    TextView txtBlock, txtPyramid, txtTube;
    Button btnVolume;

    public VolumeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_volume, container, false);
        inputLength = v.findViewById(R.id.inputLength);
        inputWide = v.findViewById(R.id.inputWide);
        inputTall = v.findViewById(R.id.inputTall);
        inputBaseArea = v.findViewById(R.id.inputBaseArea);
        inputRadius = v.findViewById(R.id.inputRadius);
        txtBlock = v.findViewById(R.id.txtBlock);
        txtPyramid = v.findViewById(R.id.txtPyramid);
        txtTube = v.findViewById(R.id.txtTube);
        btnVolume = v.findViewById(R.id.btnVolume);

        btnVolume.setOnClickListener(view -> {
            Integer length = Integer.parseInt(inputLength.getText().toString());
            Integer wide = Integer.parseInt(inputWide.getText().toString());
            Integer tall = Integer.parseInt(inputTall.getText().toString());
            Integer baseArea = Integer.parseInt(inputBaseArea.getText().toString());
            Integer radius = Integer.parseInt(inputRadius.getText().toString());

            volumeBlock(length, wide, tall);

            volumePyramid(baseArea, tall);

            volumeTube(radius, tall);
        });

        return v;
    }

    public void volumeBlock(Integer length, Integer wide, Integer tall) {
        block = (float) (length * wide * tall);
        txtBlock.setText(String.format("Balok: %s", block));
    }

    public void volumePyramid(Integer base_area, Integer tall) {
        pyramid = (float) (base_area * tall) * 1 / 3;
        txtPyramid.setText(String.format("Piramida: %s", pyramid));
    }

    public void volumeTube(Integer radius, Integer tall) {
        tube = (float) (22 / 7 * (radius * radius) * tall);
        txtTube.setText(String.format("Tabung: %s", tube));
    }
}