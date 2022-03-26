package co.tz.infowise.e_pesamanager.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONObjectUtil {

    public static JSONObject getLogonJsonObject(String userName, String password) throws JSONException {

        JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", userName);
            jsonBody.put("password", password);

        return jsonBody;
    }


    public static JSONObject getSearchedTigoTransactionsObject() throws JSONException {
        return new JSONObject();
    }
}
