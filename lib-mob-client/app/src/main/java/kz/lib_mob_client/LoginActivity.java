package kz.lib_mob_client;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Date;

import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.network.ServiceAuth;
import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.entity.AccessToken;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.entity.UserInfo;
import kz.lib_mob_client.entity.Users;
import kz.lib_mob_client.network.NetworkServiceAuth;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class LoginActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public final static String URL_AUTH = "http://192.168.1.111:9000/oauth/token?";
    public final static String CLIENT_ID = "clientId";
    public final static String CLIENT_SECRET = "secret";

    int DIALOG_DATE = 1;

    private boolean mIsSignUp;

    EditText etLogin;
    EditText etPassword;

    Button btnLogin;
    TextView tvOut;
//    String errorMsg;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvOut = findViewById(R.id.tvOut);
        setupView();

//        Bundle arguments = getIntent().getExtras();
//        if (arguments != null) {
//            String username = arguments.get("USER_NAME").toString();
//            etLogin.setText(username);
//        }

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        tokenManager.deleteToken();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void doLogin(){
        final String login = etLogin.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Введите логин и пароль!", Toast.LENGTH_SHORT).show();
            return;
        }
        ServiceAuth.createService(ServiceApi.class).
                login("password", login, password).
                enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful()){

                            tokenManager.saveToken(response.body());
                            Log.i("LOGIN", "SUCCESSFUL! " +  tokenManager.getToken().getAccess_token());
                            onShowSecondActivity(login);
                        } else {
                            try {
                                Toast.makeText(LoginActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.i("LOGIN", "FAILED!");
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Error", t.getMessage());
                    }
                });
//        OAuth2Client.Builder builder = new OAuth2Client.Builder(CLIENT_ID, CLIENT_SECRET, URL_AUTH)
//                .grantType("password")
//                .username(login)
//                .password(password);
//        OAuth2Client client = builder.build();
        /*client.requestAccessToken(new OAuthResponseCallback() {
            @Override
            public void onResponse(OAuthResponse response) {
                if (response.isSuccessful()) {
                    String accessToken = response.getAccessToken();
//                    onGetUserInfo(login, accessToken);
                    onShowSecondActivity(accessToken, login);
                } else {
                    OAuthError error = response.getOAuthError();
                    String errorMsg = response.getOAuthError().getError();
//                    response.getCode();
                    Log.i("LOGIN FAIL", ""+errorMsg);
                    tvOut.setText(""+errorMsg);
                }
            }
        });*/
        /*OAuthResponse response = null;
        try {
            response = client.requestAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String accessToken = null;
//        String refreshToken;
        String errorMsg = null;

        if (response.isSuccessful()) {
            accessToken = response.getAccessToken();
//            refreshToken = response.getRefreshToken();
            onShowSecondActivity(accessToken, login);
        } else {
            OAuthError error = response.getOAuthError();
            errorMsg = error.getError();
            response.getCode();
            Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }*/
    }



//    public void onTestButtonClick(View v){
//        Toast.makeText(LoginActivity.this, spSecretQuestion.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//    }

//    private void clearEditTexts(){
//        etLogin.getText().clear();
//        etPassword.getText().clear();
//    }

    public void onShowSecondActivity(String username){
//        NetworkServiceResource.getInstance().setAccessToken(token);
//        NetworkServiceAuth.getInstance().setAccessToken(token);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    private void setupView() {
        btnLogin.setText("Войти");
        tvOut.setText("Зарегистрироваться");
        btnLogin.setOnClickListener(doLoginClickListener);
        tvOut.setOnClickListener(showSignUpFormClickListener);
    }

    private final View.OnClickListener showSignUpFormClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);;
        }
    };

    private final View.OnClickListener doLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doLogin();
        }
    };

    public void onDateClick(View view) {
        showDialog(DIALOG_DATE);
    }

}
