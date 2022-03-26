package co.tz.infowise.e_pesamanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import co.tz.infowise.e_pesamanager.R;
import co.tz.infowise.e_pesamanager.models.Token;


public class Utility {

    public static void saveSharedPreferencesTokens(Context context, Token token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(String.valueOf(R.string.tokens_preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(token);
        sharedPreferenceEditor.putString("tokens_json", json);
        sharedPreferenceEditor.apply();
    }

    public static Token loadSharedPreferencesTokens(Context context) {
        Token token = new Token();
        SharedPreferences sharedPreference = context.getSharedPreferences(String.valueOf(R.string.tokens_preference_file_key), context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreference.getString("tokens_json", "");
        if (json != null && !json.isEmpty()) {
            Type type = new TypeToken<Token>() {
            }.getType();
            token = gson.fromJson(json, type);
        }
        return token;
    }
}
