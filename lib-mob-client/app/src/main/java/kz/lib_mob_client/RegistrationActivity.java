package kz.lib_mob_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.w3c.dom.CDATASection;

import java.io.IOException;
import java.io.Serializable;

import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.entity.UserInfo;
import kz.lib_mob_client.entity.Users;
import kz.lib_mob_client.fragments.RegStepOneFragment;
import kz.lib_mob_client.network.ServiceAuth;
import kz.lib_mob_client.utils.DataManager;
import kz.lib_mob_client.utils.RegStepAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements StepperLayout.StepperListener, DataManager {

    private StepperLayout stepperLayout;
    private String data;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    private static final String DATA = "data";
    private static final String USERS = "users";
    private static final String USER_INFO = "user_info";
    public final static String USERNAME = "USER_NAME";
    private Users users;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        int startingStepPosition = 0;
        if (savedInstanceState != null) {
            data = savedInstanceState.getString(DATA);
            startingStepPosition = savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY);
        }
        stepperLayout = findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(new RegStepAdapter(getSupportFragmentManager(), this), startingStepPosition);
        stepperLayout.setListener(this);
//        stepperLayout = findViewById(R.id.stepperLayout);
//        stepperLayout.setAdapter(new RegStepAdapter(getSupportFragmentManager(), this));
//        stepperLayout.setListener(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, stepperLayout.getCurrentStepPosition());
        outState.putSerializable(USERS, users);
        outState.putSerializable(USER_INFO, userInfo);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        int currentStepPosition = stepperLayout.getCurrentStepPosition();
        if (currentStepPosition > 0) {
            stepperLayout.onBackClicked();
        } else {
            finish();
        }
    }

    @Override
    public void onCompleted(View completeButton) {
//        Log.i("COMPLETE REG - ", users.getUsername());
//        onShowSecondActivity(users.getUsername());
        onShowSecondActivity(users.getUsername());

    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(this, verificationError.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {
//        Toast.makeText(this, "onStepSelected! -> " + newStepPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void saveUser(Users users) {
        this.users = users;
    }

    @Override
    public Users getUsers() {
        return users;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void onShowSecondActivity(String username){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(USERNAME, username);
        startActivity(intent);
    }
}