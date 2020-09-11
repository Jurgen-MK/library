package kz.lib_mob_client.auth_utils;

import android.content.SharedPreferences;

import kz.lib_mob_client.entity.AccessToken;

public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if (INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return  INSTANCE;
    }

    public void saveToken(AccessToken accessToken){
        editor.putString("ACCESS_TOKEN", accessToken.getAccess_token()).commit();
        editor.putString("REFRESH_TOKEN", accessToken.getRefresh_token()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setAccess_token(prefs.getString("ACCESS_TOKEN", null));
        token.setRefresh_token(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }
}
