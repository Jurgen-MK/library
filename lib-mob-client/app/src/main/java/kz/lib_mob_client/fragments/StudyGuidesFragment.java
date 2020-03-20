package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kz.lib_mob_client.R;
import kz.lib_mob_client.entity.RegulatoryDocumentation;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudyGuidesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudyGuidesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<RegulatoryDocumentation> regDocList;

    public StudyGuidesFragment() {
        // Required empty public constructor
    }

    public static StudyGuidesFragment newInstance(String param1, String param2) {
        StudyGuidesFragment fragment = new StudyGuidesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        regDocList = new ArrayList<RegulatoryDocumentation>();
        Log.i("huitatus", "huitatus");
        try {
            regDocList = NetworkServiceResource.getInstance().getJSONApi().getRegDocList("Bearer " + NetworkServiceAuth.getInstance().getAccessToken()).execute().body();
            for (RegulatoryDocumentation rd: regDocList) {
                Log.i("huitatus",String.valueOf(rd.getId()));
                Log.i("huitatus", rd.getText());
            }
            Log.i("huitag", "Size - " + regDocList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_study_guides, container, false);
    }
}
