package co.tz.infowise.e_pesamanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.tz.infowise.e_pesamanager.adapters.TigoAdapter;
import co.tz.infowise.e_pesamanager.models.TigoTransactions;
import co.tz.infowise.e_pesamanager.models.Token;
import co.tz.infowise.e_pesamanager.utils.JSONObjectUtil;
import co.tz.infowise.e_pesamanager.utils.JSONUtil;
import co.tz.infowise.e_pesamanager.utils.Utility;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TigoAdapter tigoAdapter;
    ArrayList<TigoTransactions> tigoTransactions = new ArrayList<>();

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

        recyclerView = findViewById(R.id.recyclerView);


    }

    public void buildRecyclerView(ArrayList<TigoTransactions> transactions) {
        tigoAdapter = new TigoAdapter(getApplicationContext(),tigoTransactions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tigoAdapter);

    }

    public void searchSalesOrders(Token token) throws JSONException {
        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = getString(R.string.API_url_tigo_transactions);;

        JSONArray json = JSONObjectUtil.getSearchedTigoTransactionsObject();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,json, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray res) {

                try {
                    tigoTransactions= JSONUtil.loadTigoTransactionsFromJson(res);
                    buildRecyclerView(tigoTransactions);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("--------------  "+res);

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
        requestQueue.add(jsonArrayRequest);
    }

}