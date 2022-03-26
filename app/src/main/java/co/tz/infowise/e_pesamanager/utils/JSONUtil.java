package co.tz.infowise.e_pesamanager.utils;

import org.json.JSONException;
import org.json.JSONObject;

import co.tz.infowise.e_pesamanager.models.Token;

public class JSONUtil {

    public static Token loadUserFromJson(JSONObject jsonObject) throws JSONException {
//        JSONObject data = new JSONObject(jsonObject.getString(""));
        Token token = new Token();
        token.setToken(jsonObject.getString("token"));
        return token;
    }

//    public static User loadUserFromJson(JSONObject jsonObject) throws JSONException {
//        User user = new User();
//        JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject data = jsonArray.getJSONObject(i);
//            user.setId(data.getLong("id"));
//            user.setUsername(data.getString("userName"));
//            user.setType(data.getString("type"));
//            user.setRoles(data.getString("roles"));
//            user.setFullName(data.getString("fullName"));
//
//            }
//        }
//
//        return user;
//    }
}
