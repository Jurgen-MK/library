package kz.lib_mob_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import kz.lib_mob_client.fragments.AllBookFragment;
import kz.lib_mob_client.fragments.LearningMenuFragment;
import kz.lib_mob_client.fragments.RegulatoryMenuFragment;
import kz.lib_mob_client.fragments.RegulatoryDocumentationFragment;
import kz.lib_mob_client.fragments.TechnicalMenuFragment;

public class MainActivity extends AppCompatActivity {

    RegulatoryMenuFragment regfrag;
    TechnicalMenuFragment techfrag;
    LearningMenuFragment learfrag;
    RegulatoryDocumentationFragment rdf;
    AllBookFragment abf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regfrag = new RegulatoryMenuFragment();
        techfrag = new TechnicalMenuFragment();
        learfrag = new LearningMenuFragment();
        BoomMenuButton bmb = findViewById(R.id.bmb);
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.filesfolders)
                .normalTextRes(R.string.MenuItem1)
                .normalColor(Color.parseColor("#FF82B1FF"))
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(regfrag);
                        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frgmCont, regfrag);
                        fragmentTransaction.commit();*/
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.pencil)
                .normalTextRes(R.string.MenuItem2)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(techfrag);
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.thinking)
                .normalTextRes(R.string.MenuItem3)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(learfrag);
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.laboratory)
                .normalTextRes(R.string.MenuItem4)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.museum)
                .normalTextRes(R.string.MenuItem5)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
    }

    public void onRegMenuBtnClick(View v) {
        rdf = new RegulatoryDocumentationFragment();
        Bundle args = new Bundle();

        switch (v.getId()) {
            case R.id.lawsbtn:
                args.putInt("arg_category", 1);
                break;
            case R.id.codebtn:
                args.putInt("arg_category", 2);
                break;
            case R.id.regbtn:
                args.putInt("arg_category", 3);
                break;
            case R.id.devprogtn:
                args.putInt("arg_category", 4);
                break;
            case R.id.decreebtn:
                args.putInt("arg_category", 5);
                break;
            case R.id.intdocbtn:
                args.putInt("arg_category", 6);
                break;
            case R.id.instrbtn:
                args.putInt("arg_category", 1);
                break;
            case R.id.guidebtn:
                args.putInt("arg_category", 13);
                break;
            case R.id.provbtn:
                args.putInt("arg_category", 14);
                break;
            case R.id.rulesbtn:
                args.putInt("arg_category", 15);
                break;
            case R.id.standartsbtn:
                args.putInt("arg_category", 16);
                break;
            case R.id.techmapbtn:
                args.putInt("arg_category", 17);
                break;
            case R.id.normbtn:
                args.putInt("arg_category", 18);
                break;
            case R.id.refsbtn:
                args.putInt("arg_category", 20);
                break;
            case R.id.lpbtn:
                args.putInt("arg_category", 21);
                break;
            case R.id.articlesbtn:
                args.putInt("arg_category", 22);
                break;
            case R.id.moviesbtn:
                args.putInt("arg_category", 23);
                break;
        }
        rdf.setArguments(args);
        displayFragment(rdf);
    }

    public void onBookMenuBtnClick(View v) {
        abf = new AllBookFragment();
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.bookbtn:
                args.putInt("arg_status", 1);
                break;
            case R.id.learnbtn:
                args.putInt("arg_status", 2);
                break;
            case R.id.refsbtn:
                args.putInt("arg_status", 3);
                break;
        }
        abf.setArguments(args);
        displayFragment(abf);

    }

   /* public void onCodeBtnClick(View v){
        rdf = new RegulatoryDocumentationFragment();
        Bundle args = new Bundle();
        args.putInt("arg_category", 2);
        rdf.setArguments(args);
        displayFragment(rdf);
    }*/

    private void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, fragment);
        fragmentTransaction.commit();
    }

}
