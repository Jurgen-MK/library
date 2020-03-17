package kz.lib_mob_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

public class MainActivity extends AppCompatActivity {

   BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoomMenuButton bmb = findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_launcher_foreground)
                    .normalTextRes(R.string.app_name)
                    .subNormalTextRes(R.string.app_name);
            bmb.addBuilder(builder);
        }
    }
}
