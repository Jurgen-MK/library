package kz.lib_mob_client.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import kz.lib_mob_client.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewDialogFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "newstext";


    // TODO: Rename and change types of parameters
    private String newstext;


    WebView webView;

    public WebViewDialogFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewDialogFragmen.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewDialogFragment newInstance(String param1) {
        WebViewDialogFragment fragment = new WebViewDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newstext = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_view_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.webView);
        //String text = "<p><strong>В социальных сетях и мессенджерах было распространено видео, на котором железнодорожники спасались на деревьях от диких животных.</strong></p><p><br></p><p>Как выяснилось, 5 февраля текущего года на перегоне «Акколь - Шортанды» трое монтеров Акмолинского отделения магистральной сети производили работы по вскрытию рельсовых стыков от снега.</p><p>Около полудня рабочие увидели стаю из 8 животных, внешне похожих на волков или на диких собак. Они приближались к ним со стороны леса. Рабочие забрались на близлежащие деревья, откуда по телефону доложили о сложившейся ситуации в диспетчерскую службу.</p><p>Так как компания уделяет большое внимание обеспечению безопасности своих сотрудников, за рабочими сразу же со станции Акколь был отправлен локомотив. Звуковые сигналы отогнали животных. Монтеры были доставлены на ближайший переезд, их здоровью ничего не угрожает.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p>Соответствующие обращения о данном факте были направлены в Управление природных ресурсов и регулирования природопользования Акмолинской области, акиматы Шортандинского и Аккольского районов, а также в территориальные органы полиции.</p><p>Рабочий процесс на указанном перегоне проходит в штатном режиме.</p><p><br></p><p><strong><em>Пресс-служба филиала АО «НК «ҚТЖ» - «Дирекция магистральной сети»</em></strong></p><p><br></p>";
        webView.loadDataWithBaseURL(null, newstext, "text/html", "utf-8", null);
    }

}
