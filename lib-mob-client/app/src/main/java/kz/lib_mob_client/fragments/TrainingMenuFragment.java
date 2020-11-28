package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kz.lib_mob_client.R;


public class TrainingMenuFragment extends Fragment {




    public TrainingMenuFragment() {
        // Required empty public constructor
    }

    public static TrainingMenuFragment newInstance() {
        TrainingMenuFragment fragment = new TrainingMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training_menu, container, false);
    }
}