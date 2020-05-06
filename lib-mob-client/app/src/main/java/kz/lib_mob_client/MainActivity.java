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

import kz.lib_mob_client.fragments.RegulatoryMenuFragment;
import kz.lib_mob_client.fragments.RegulatoryDocumentationFragment;

public class MainActivity extends AppCompatActivity {

    RegulatoryMenuFragment regfrag;
    RegulatoryDocumentationFragment rdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regfrag = new RegulatoryMenuFragment();
        BoomMenuButton bmb = findViewById(R.id.bmb);
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.binder)
                .normalTextRes(R.string.MenuItem1)
                .normalColor(Color.parseColor("#FF82B1FF"))
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frgmCont, regfrag);
                        fragmentTransaction.commit();
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.book)
                .normalTextRes(R.string.MenuItem2)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.film)
                .normalTextRes(R.string.MenuItem3)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.circular)
                .normalTextRes(R.string.MenuItem4)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.circular)
                .normalTextRes(R.string.placeholder)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF")));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.circular)
                .normalTextRes(R.string.placeholder)
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
        }
        //args.putInt("arg_category", 1);
        rdf.setArguments(args);
        displayFragment(rdf);
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
