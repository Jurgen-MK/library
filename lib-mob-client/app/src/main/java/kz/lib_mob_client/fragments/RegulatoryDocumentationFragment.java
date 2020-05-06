package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import kz.lib_mob_client.R;
import kz.lib_mob_client.entity.RegulatoryDocumentation;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegulatoryDocumentationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegulatoryDocumentationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "arg_category";


    // TODO: Rename and change types of parameters
    private int category;


    List<RegulatoryDocumentation> regDocList;

    RecyclerView rv;
    TextView titleText;


    public RegulatoryDocumentationFragment() {
        // Required empty public constructor
    }

    public static RegulatoryDocumentationFragment newInstance(int category) {
        RegulatoryDocumentationFragment fragment = new RegulatoryDocumentationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_CATEGORY);
        }
        Log.i("huitatusargument", String.valueOf(category));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        regDocList = new ArrayList<>();
        try {
            regDocList = NetworkServiceResource.getInstance().getJSONApi().getRegDocList("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), category).execute().body();
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        titleText = view.findViewById(R.id.titleText);
        switchCategoryTitle();
        List<IFlexible> list = new ArrayList<>();
        list.addAll(regDocList);
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
        rv = view.findViewById(R.id.rv);
        adapter.updateDataSet(list, true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

    }

    public void switchCategoryTitle(){
        switch (category){
            case 1:
                titleText.setText("Законы");
                break;
            case 2:
                titleText.setText("Кодексы");
                break;
            case 3:
                titleText.setText("Постановления");
                break;
            case 4:
                titleText.setText("Программы развития");
                break;
            case 5:
                titleText.setText("Указы");
                break;
            case 6:
                titleText.setText("Международные документы");
                break;
            case 13:
                titleText.setText("Методические указания");
                break;
            case 14:
                titleText.setText("Положения, руководства. Технические указания");
                break;
            case 15:
                titleText.setText("Правила");
                break;
            case 16:
                titleText.setText("Стандарты организации. Технические условия");
                break;
            case 17:
                titleText.setText("Технические процессы. Технологические карты");
                break;
            case 18:
                titleText.setText("Типовые нормы. Нормативы");
                break;
        }
    }


}
