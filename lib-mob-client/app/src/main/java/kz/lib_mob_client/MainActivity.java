package kz.lib_mob_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity{

   BoomMenuButton bmb;
   RegulatoryFragment regfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regfrag = new RegulatoryFragment();
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
                    }}));
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

    }

}
