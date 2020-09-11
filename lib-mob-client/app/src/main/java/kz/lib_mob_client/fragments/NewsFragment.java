package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import kz.lib_mob_client.R;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.network.ServiceAuth;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.entity.News;
import retrofit2.Call;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    WebView webView;
    RecyclerView rv;
    List<News> listNews;
    TokenManager tokenManager;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        tokenManager = TokenManager.getInstance(getContext().getSharedPreferences("prefs", getContext().MODE_PRIVATE));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listNews = new ArrayList<>();
        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class, tokenManager);
        Call<List<News>> callRes = serviceApi.getNews();

        try {
            listNews = callRes.execute().body();
//            listNews = NetworkService.getInstance().getJSONApi().getNewsList("Bearer " + NetworkServiceAuth.getInstance().getAccessToken()).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        List<IFlexible> list = new ArrayList<>();
        list.addAll(listNews);
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(list);
        rv = view.findViewById(R.id.rvNews);
        adapter.updateDataSet(list, true);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);
        /*webView = view.findViewById(R.id.webView1);
        String text = "<p><strong>В социальных сетях и мессенджерах было распространено видео, на котором железнодорожники спасались на деревьях от диких животных.</strong></p><p><br></p><p>Как выяснилось, 5 февраля текущего года на перегоне «Акколь - Шортанды» трое монтеров Акмолинского отделения магистральной сети производили работы по вскрытию рельсовых стыков от снега.</p><p>Около полудня рабочие увидели стаю из 8 животных, внешне похожих на волков или на диких собак. Они приближались к ним со стороны леса. Рабочие забрались на близлежащие деревья, откуда по телефону доложили о сложившейся ситуации в диспетчерскую службу.</p><p>Так как компания уделяет большое внимание обеспечению безопасности своих сотрудников, за рабочими сразу же со станции Акколь был отправлен локомотив. Звуковые сигналы отогнали животных. Монтеры были доставлены на ближайший переезд, их здоровью ничего не угрожает.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>Соответствующие обращения о данном факте были направлены в Управление природных ресурсов и регулирования природопользования Акмолинской области, акиматы Шортандинского и Аккольского районов, а также в территориальные органы полиции.</p><p>Рабочий процесс на указанном перегоне проходит в штатном режиме.</p><p><br></p><p><strong><em>Пресс-служба филиала АО «НК «ҚТЖ» - «Дирекция магистральной сети»</em></strong></p><p><br></p>";
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);*/
    }

}
