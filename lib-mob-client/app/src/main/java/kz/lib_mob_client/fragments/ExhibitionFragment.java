package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.utils.Log;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.ExhibitionRespond;
import kz.lib_mob_client.network.ServiceAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExhibitionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExhibitionFragment extends Fragment {

    RecyclerView rvRespond;
    TokenManager tokenManager;
    List<ExhibitionRespond> listExRespond;

    public ExhibitionFragment() {

    }

    public static ExhibitionFragment newInstance(String param1, String param2) {
        ExhibitionFragment fragment = new ExhibitionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", getContext().MODE_PRIVATE));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exhibition, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvRespond = view.findViewById(R.id.rvRespond);
        listExRespond = new ArrayList<>();
        try {
            listExRespond = ServiceAuth.createService(ServiceApi.class, tokenManager).getAllExhibitions().execute().body();
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            List<IFlexible> list = new ArrayList<>();
            list.addAll(listExRespond);
            FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
            adapter.updateDataSet(list, true);
            rvRespond.setAdapter(adapter);
            rvRespond.setLayoutManager(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}