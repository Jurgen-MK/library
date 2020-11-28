package kz.lib_mob_client;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import kz.lib_mob_client.fragments.AllBookFragment;
import kz.lib_mob_client.fragments.ExhibitionFragment;
import kz.lib_mob_client.fragments.InnovationReportFragment;
import kz.lib_mob_client.fragments.LearningMenuFragment;
import kz.lib_mob_client.fragments.MainMenuFragment;
import kz.lib_mob_client.fragments.MuseumFragment;
import kz.lib_mob_client.fragments.MuseumPublishingMenu;
import kz.lib_mob_client.fragments.NewsFragment;
import kz.lib_mob_client.fragments.PublishingProductsFragment;
import kz.lib_mob_client.fragments.RegulatoryMenuFragment;
import kz.lib_mob_client.fragments.RegulatoryDocumentationFragment;
import kz.lib_mob_client.fragments.SearchFragment;
import kz.lib_mob_client.fragments.TechnicalMenuFragment;
import kz.lib_mob_client.fragments.TrainingMenuFragment;
import kz.lib_mob_client.utils.SAFUtils;

public class MainActivity extends AppCompatActivity {

   /* RegulatoryMenuFragment regfrag;
    TechnicalMenuFragment techfrag;
    LearningMenuFragment learfrag;
    RegulatoryDocumentationFragment rdf;
    AllBookFragment abf;*/
    SearchFragment sf;
    NewsFragment nf;
    InnovationReportFragment irf;
    ExhibitionFragment exf;
    MuseumFragment mf;
    PublishingProductsFragment ppf;
    MainMenuFragment mmf;
    MuseumPublishingMenu mpmf;
    TrainingMenuFragment tmf;
    private int SETTINGS_REQUEST_CODE = 1;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*regfrag = new RegulatoryMenuFragment();
        techfrag = new TechnicalMenuFragment();
        learfrag = new LearningMenuFragment();
        sf = new SearchFragment();
        nf = new NewsFragment();
        irf = new InnovationReportFragment();
        exf = new ExhibitionFragment();
        mf = new MuseumFragment();
        ppf = new PublishingProductsFragment();*/
        BoomMenuButton bmb = findViewById(R.id.bmb);

        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.filesfolders)
                .normalTextRes(R.string.MenuItem1)
                .normalColor(Color.parseColor("#FF82B1FF"))
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        mmf = new MainMenuFragment();
                        displayFragment(mmf);
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
                        mpmf = new MuseumPublishingMenu();
                        displayFragment(mpmf);
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.pencil)
                .normalTextRes(R.string.MenuItem6)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        tmf = new TrainingMenuFragment();
                        displayFragment(tmf);
                    }
                }));
        /*bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.pencil)
                .normalTextRes(R.string.MenuItem7)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(exf);
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.pencil)
                .normalTextRes(R.string.MenuItem8)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(mf);
                    }
                }));
        bmb.addBuilder(new HamButton.Builder()
                .normalImageRes(R.drawable.pencil)
                .normalTextRes(R.string.MenuItem9)
                .imageRect(new Rect(40, 40, Util.dp2px(50), Util.dp2px(50)))
                .normalColor(Color.parseColor("#FF82B1FF"))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        displayFragment(ppf);
                    }
                }));*/

        /*bmb.addBuilder(new HamButton.Builder()
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
                .normalColor(Color.parseColor("#FF82B1FF")));*/
    }

    public void onMenuSearchBtnClick(View v){
       sf = new SearchFragment();
       displayFragment(sf);
    }

    public void onMenuNewsBtnClick(View v){
        nf = new NewsFragment();
        displayFragment(nf);
    }

    public void onMenuMuseumBtnClick(View v){
        mf = new MuseumFragment();
        displayFragment(mf);
    }

    public void onMenuExhibitionBtnClick(View v){
        exf = new ExhibitionFragment();
        displayFragment(exf);
    }

    public void onMenuPublishingProductsBtnClick(View v){
        ppf = new PublishingProductsFragment();
        displayFragment(ppf);
    }

    /*public void onRegMenuBtnClick(View v) {
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

    }*/

   /* public void onCodeBtnClick(View v){
        rdf = new RegulatoryDocumentationFragment();
        Bundle args = new Bundle();
        args.putInt("arg_category", 2);
        rdf.setArguments(args);
        displayFragment(rdf);
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        Dexter.withActivity(MainActivity.this).
                withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Toast.makeText(MainActivity.this, "Картинка выбрана!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        showSettingsDialog();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).onSameThread().check();
    }*/

    private void displayFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, fragment);
//        String frName = fragment.getClass().getCanonicalName();
//        Log.i("Fragment Name ", frName);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Нужно разрешение");
        builder.setMessage("Этому приложению требуется разрешение для доступа файловой системе. Вы можете дать разрешение в настройках");
        builder.setPositiveButton("НАСТРОЙКИ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", "kz.lib_mob_client", null);
        intent.setData(uri);
        startActivityForResult(intent, SETTINGS_REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                backToast.cancel();
                super.onBackPressed();
                return;
            } else {
                backToast = Toast.makeText(this, "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
