package com.example.lenovo.bio_calculator.FunctionFragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.bio_calculator.MainActivity;
import com.example.lenovo.bio_calculator.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q_AFragment extends Fragment {


    public Q_AFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(47, 85, 151)));

        return inflater.inflate(R.layout.fragment_q__a, container, false);
    }

}
