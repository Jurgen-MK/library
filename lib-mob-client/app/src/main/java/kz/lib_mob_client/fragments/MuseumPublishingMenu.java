package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kz.lib_mob_client.R;


public class MuseumPublishingMenu extends Fragment {



    public MuseumPublishingMenu() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MuseumPublishingMenu newInstance() {
        MuseumPublishingMenu fragment = new MuseumPublishingMenu();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_museum_publishing_menu, container, false);
    }
}