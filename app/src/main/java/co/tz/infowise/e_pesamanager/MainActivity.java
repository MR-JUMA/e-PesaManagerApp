package co.tz.infowise.e_pesamanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.tz.infowise.e_pesamanager.models.Token;
import co.tz.infowise.e_pesamanager.utils.JSONObjectUtil;
import co.tz.infowise.e_pesamanager.utils.Utility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Token token= Utility.loadSharedPreferencesTokens(getApplicationContext());
        try {
            searchSalesOrders(token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void searchSalesOrders(Token token) throws JSONException {
        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = getString(R.string.API_url_tigo_transactions);;

        JSONObject json = JSONObjectUtil.getSearchedTigoTransactionsObject();

        JsonObjectRequest saleOrderObjectRequest = new JsonObjectRequest(Request.Method.GET, url,json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject res) {
                System.out.println("--------------  "+res);
//                    utility.saveSaleOrdersAccessedServer(context, true);
//                    final ArrayList<SaleOrder> orders = jsonUtil.loadSalesOrdersFromJSONObject(res);
//                    utility.saveSharedPreferencesSalesOrders(context.getApplicationContext(), orders, user.getUsername());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization",token.getToken());
                return params;
            }
        };
        requestQueue.add(saleOrderObjectRequest);
    }

}