package com.example.lenovo.bio_calculator.FunctionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.bio_calculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Gel_CalFragment extends Fragment {

    @BindView(R.id.edit_b)
    EditText edit_b;
    @BindView(R.id.text_1)
    TextView text_1;
    @BindView(R.id.text_2)
    TextView text_2;
    @BindView(R.id.text_3)
    TextView text_3;
    @BindView(R.id.text_4)
    TextView text_4;
    @BindView(R.id.text_5)
    TextView text_5;
    @BindView(R.id.text_6)
    TextView text_6;

    @BindView(R.id.gel_mainview)
    LinearLayout gel_mainview;


    public Gel_CalFragment() {
        // Required empty public constructor
    }

    double value1;
    double value2;
    double value3;
    double value4;
    double value5;
    double value6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gel_cal, container, false);
        ButterKnife.bind(this, view);

        edit_b.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text = s.toString();
                if (text.length() == 0) {
                    text_1.setText(null);
                    text_2.setText(null);
                    text_3.setText(null);
                    text_4.setText(null);
                    text_5.setText(null);
                    text_6.setText(null);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (edit_b.getText().toString().length() != 0) {
                    value1 = (Double.parseDouble(edit_b.getText().toString()) * 0.17);
                    value2 = (Double.parseDouble(edit_b.getText().toString()) * 0.125);
                    value3 = (Double.parseDouble(edit_b.getText().toString()) * 0.01);
                    value4 = (Double.parseDouble(edit_b.getText().toString()) * 0.68);
                    value5 = (Double.parseDouble(edit_b.getText().toString()) * 0.01);
                    value6 = (Double.parseDouble(edit_b.getText().toString()) * 0.001);

                    text_1.setText(String.valueOf(value1));
                    text_2.setText(String.valueOf(value2));
                    text_3.setText(String.valueOf(value3));
                    text_4.setText(String.valueOf(value4));
                    text_5.setText(String.valueOf(value5));
                    text_6.setText(String.valueOf(value6));
                }

                if (text.length() == 0) {
                    text_1.setText(null);
                    text_2.setText(null);
                    text_3.setText(null);
                    text_4.setText(null);
                    text_5.setText(null);
                    text_6.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.length() == 0) {
                    text_1.setText(null);
                    text_2.setText(null);
                    text_3.setText(null);
                    text_4.setText(null);
                    text_5.setText(null);
                    text_6.setText(null);
                }

            }
        });
        return view;
    }

    @OnClick(R.id.gel_mainview)
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(gel_mainview.getWindowToken(), 0);
    }

}
