package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
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
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.AllBook;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.network.ServiceAuth;
import retrofit2.Call;

public class AllBookFragment extends Fragment {
    private static final String ARG_STATUS = "arg_status";


    private int status;
    RecyclerView rv;
    List<AllBook> allBookList;
    TextView titleText;
    TokenManager tokenManager;

    public AllBookFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllBookFragment newInstance(String param1, String param2) {
        AllBookFragment fragment = new AllBookFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getInt(ARG_STATUS);
        }
        tokenManager = TokenManager.getInstance(getContext().getSharedPreferences("prefs", getContext().MODE_PRIVATE));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        allBookList = new ArrayList<>();
        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class, tokenManager);
        Call<List<AllBook>> call = serviceApi.getAllBookByStatus(status);
        try {
            allBookList = call.execute().body();
//            allBookList = NetworkServiceResource.getInstance().getJSONApi().getAllBookByStatus("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), status).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_book, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        titleText = view.findViewById(R.id.titleText);
        switchCategoryTitle();
        List<IFlexible> list = new ArrayList<>();
        list.addAll(allBookList);
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
        rv = view.findViewById(R.id.rv);
        adapter.updateDataSet(list, true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

    }

    public void switchCategoryTitle() {
        switch (status) {
            case 1:
                titleText.setText("Учебники, книги");
                break;
            case 2:
                titleText.setText("Учебные пособия");
                break;
            case 3:
                titleText.setText("Справочники, словари");
                break;
        }
    }

}
