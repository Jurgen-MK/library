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
    EditText etPasswordConfirm;
    EditText etSurname;
    EditText etName;
    EditText etPatr;
    EditText etPhone;
    EditText etEmail;
    EditText etBDate;
    EditText etAnswer;
    Button btnLogin;
    TextView tvOut;
    Spinner spSecretQuestion;
//    String errorMsg;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        etSurname = findViewById(R.id.etSurname);
        etName = findViewById(R.id.etName);
        etPatr = findViewById(R.id.etPatr);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etBDate = findViewById(R.id.etBDate);
        btnLogin = findViewById(R.id.btnLogin);
        tvOut = findViewById(R.id.tvOut);
        etAnswer = findViewById(R.id.etAnswer);
        spSecretQuestion = findViewById(R.id.spSecretQuestion);
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("____-__-__");
        FormatWatcher formatWatcher = new MaskFormatWatcher( // форматировать текст будет вот он
                MaskImpl.createTerminated(slots)
        );
        formatWatcher.installOn(etBDate);
        setupView(mIsSignUp);

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
        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class);
        Call<AccessToken> call = serviceApi.login("password", login, password);
        call.enqueue(new Callback<AccessToken>() {
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

    public void doSignUp(){
        final String login = etLogin.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String passwordConfirm = etPasswordConfirm.getText().toString().trim();
        String fullName = etSurname.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String position = etBDate.getText().toString().trim();
        Date bdate = new Date();

        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Введите логин и пароль!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.equals(password, passwordConfirm)){
            Toast.makeText(LoginActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(position) || TextUtils.isEmpty(etName.getText().toString())
                || TextUtils.isEmpty(etPatr.getText().toString()) || TextUtils.isEmpty(etBDate.getText().toString())
                || TextUtils.isEmpty(etAnswer.getText().toString()) || TextUtils.isEmpty(etEmail.getText().toString())
                || TextUtils.isEmpty(etPhone.getText().toString())){
            Toast.makeText(LoginActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        Users user =  new Users(login, password, "", 0, 0, spSecretQuestion.getSelectedItem().toString(), etAnswer.toString(), (byte) 1);
        Log.i("huita", user.getSecretquestions());
        UserInfo userInfo = new UserInfo(0, login, etName.getText().toString().trim(), etSurname.getText().toString().trim(), etPatr.getText().toString().trim(), etBDate.getText().toString(),
                "", 0, 0, 0, 0, 0, 0, phone,
                email, "");
        ServiceApi serviceApi = ServiceAuth.createServiceRegistration(ServiceApi.class);
        Call<ResponseBody> call = serviceApi.doRegistration(new UserCreationRequest(user, userInfo));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String message = null;
                try {
                    message = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                clearEditTexts();
                setupView(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Ошибка при регистрации! " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        NetworkServiceAuth.
//            getInstance().
//            getJSONAuthApi().
//            doRegistration("application/json", new UserCreationRequest(user, userInfo)).
//            enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    String message = null;
//                    try {
//                        message = response.body().string();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
//                    clearEditTexts();
//                    setupView(false);
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(LoginActivity.this, "Ошибка при регистрации! " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
    }

    public void onTestButtonClick(View v){
        Toast.makeText(LoginActivity.this, spSecretQuestion.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    private void clearEditTexts(){
        etLogin.getText().clear();
        etPassword.getText().clear();
        etPasswordConfirm.getText().clear();
        etSurname.getText().clear();
        etPhone.getText().clear();
        etEmail.getText().clear();
        etBDate.getText().clear();
        etAnswer.getText().clear();
        etName.getText().clear();
        etPatr.getText().clear();
    }

    public void onShowSecondActivity(String username){
//        NetworkServiceResource.getInstance().setAccessToken(token);
//        NetworkServiceAuth.getInstance().setAccessToken(token);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }

    private void setupView(boolean isSignUp) {
        mIsSignUp = isSignUp;
        btnLogin.setText(isSignUp ? "Зарегистрироваться" : "Войти");
        tvOut.setText(isSignUp ? "Войти" : "Зарегистрироваться");
        etPasswordConfirm.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etSurname.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etName.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etPatr.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etPhone.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etEmail.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etBDate.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        etAnswer.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        spSecretQuestion.setVisibility(isSignUp ? View.VISIBLE : View.GONE);
        btnLogin.setOnClickListener(isSignUp ? doSignUpClickListener : doLoginClickListener);
        tvOut.setOnClickListener(isSignUp ? showLoginFormClickListener : showSignUpFormClickListener);
    }

    private final View.OnClickListener showSignUpFormClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setupView(true);
        }
    };

    private final View.OnClickListener showLoginFormClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setupView(false);
        }
    };

    private final View.OnClickListener doLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doLogin();
        }
    };

    private final View.OnClickListener doSignUpClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doSignUp();
        }
    };

    public void onDateClick(View view) {
        showDialog(DIALOG_DATE);
    }

}
