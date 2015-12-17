package com.udacity.gradle.builditbigger;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProgressSpinnerFragment extends Fragment {

    public static ProgressSpinnerFragment newInstance() {
        ProgressSpinnerFragment fragment = new ProgressSpinnerFragment();
        return fragment;
    }

    public ProgressSpinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_spinner, container, false);
    }


}
