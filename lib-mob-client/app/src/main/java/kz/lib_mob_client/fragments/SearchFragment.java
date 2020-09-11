package kz.lib_mob_client.fragments;

import android.media.session.MediaSession;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.SearchRequest;
import kz.lib_mob_client.entity.SearchRespond;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.network.ServiceAuth;
import retrofit2.Call;


public class SearchFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Spinner spCategory;
    Button searchButton;
    ImageButton downloadButton;
    TextView tvSearch;
    EditText etSearchString;
    List<SearchRespond> listSearchRespond;
    RecyclerView rvRespond;
    TokenManager tokenManager;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs", getContext().MODE_PRIVATE));
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spCategory = view.findViewById(R.id.spCategory);
        tvSearch = view.findViewById(R.id.tvSearch);
        etSearchString = view.findViewById(R.id.etSearchString);
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        listSearchRespond = new ArrayList<>();
        rvRespond = view.findViewById(R.id.rvRespond);
    }


    @Override
    public void onClick(View v) {
        SearchRequest searchRequest = new SearchRequest();
        //spCategory.getSelectedItemPosition()
        tvSearch.setText("позиция " +  (spCategory.getSelectedItemPosition()+1));
        searchRequest.setCatalogType(String.valueOf(spCategory.getSelectedItemPosition()+1));
        searchRequest.setSearchString(etSearchString.getText().toString());
        if(IntStream.rangeClosed(1,13).boxed().collect(Collectors.toList()).contains(spCategory.getSelectedItemPosition()+1)) {
            tvSearch.setText("npdntd "+ (spCategory.getSelectedItemPosition()+1));
            searchRequest.setCatalogMode("npdntd");
        }
        if(IntStream.rangeClosed(14,16).boxed().collect(Collectors.toList()).contains(spCategory.getSelectedItemPosition()+1)) {
            tvSearch.setText("allbook "+ (spCategory.getSelectedItemPosition()+1));
            searchRequest.setCatalogMode("allbook");
        }
        if((spCategory.getSelectedItemPosition()+1)==17) {
            tvSearch.setText("article "+ (spCategory.getSelectedItemPosition()+1));
            searchRequest.setCatalogMode("article");
        }
        if((spCategory.getSelectedItemPosition()+1)==18) {
            tvSearch.setText("video "+ (spCategory.getSelectedItemPosition()+1));
            searchRequest.setCatalogMode("video");
        }
        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class, tokenManager);
        Call<List<SearchRespond>> call = serviceApi.searchByCategory(searchRequest);
        try {
            listSearchRespond = call.execute().body();
//            listSearchRespond = NetworkServiceResource.getInstance().getJSONApi().searchByCategory("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(), searchRequest).execute().body();
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            List<IFlexible> list = new ArrayList<>();
            list.addAll(listSearchRespond);
            FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
            adapter.updateDataSet(list, true);
            rvRespond.setAdapter(adapter);
            rvRespond.setLayoutManager(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
