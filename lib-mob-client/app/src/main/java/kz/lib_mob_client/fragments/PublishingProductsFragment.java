package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import kz.lib_mob_client.R;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.PublishingProducts;
import kz.lib_mob_client.network.ServiceAuth;

public class PublishingProductsFragment extends Fragment {


    TokenManager tokenManager;
    List<PublishingProducts> listPubProd;
    RecyclerView rvRespond;


    public PublishingProductsFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static PublishingProductsFragment newInstance(String param1, String param2) {
        PublishingProductsFragment fragment = new PublishingProductsFragment();
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
        return inflater.inflate(R.layout.fragment_publishing_products, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listPubProd = new ArrayList<>();
        rvRespond = view.findViewById(R.id.rvRespond);
        try {
            listPubProd = ServiceAuth.createService(ServiceApi.class, tokenManager).getAllPublishingProducts().execute().body();
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            List<IFlexible> list = new ArrayList<>();
            list.addAll(listPubProd);
            FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
            adapter.updateDataSet(list, true);
            rvRespond.setAdapter(adapter);
            rvRespond.setLayoutManager(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}