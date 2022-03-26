package co.tz.infowise.e_pesamanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.tz.infowise.e_pesamanager.models.Token;
import co.tz.infowise.e_pesamanager.utils.JSONObjectUtil;
import co.tz.infowise.e_pesamanager.utils.JSONUtil;
import co.tz.infowise.e_pesamanager.utils.Utility;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username;
    TextInputEditText password;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.sign_in);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                TextInputLayout passwordTextInput = findViewById(R.id.password_text_field);
                TextInputLayout usernameTextInput = findViewById(R.id.username_text_field);

                if (user.isEmpty()) {
                    usernameTextInput.setError("required!");
                } else if (pass.isEmpty()) {
                    passwordTextInput.setError("enter valid password!");
                } else {
                    try {
                        login();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    public void login() throws JSONException {
        // Instantiate the RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = getString(R.string.API_url_user_login);

        JSONObject jsonBody = JSONObjectUtil.getLogonJsonObject(username.getText().toString(), password.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,jsonBody, new Response.Listener<JSONObject>() {


            TextInputLayout passwordTextInput = findViewById(R.id.password_text_field);
            TextInputLayout usernameTextInput = findViewById(R.id.username_text_field);


            @Override
            public void onResponse(JSONObject response) {
                try {

                    if (response != null) {
                        Token token = JSONUtil.loadUserFromJson(response);
                        Utility.saveSharedPreferencesTokens(getApplicationContext(),token);
                        openHomepage();
                    } else {

                        usernameTextInput.setError(" ");
                        passwordTextInput.setError(getString(R.string.login_error));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("----------- "+error);
                Toast.makeText(getApplicationContext(), "Internal Server Error. Please Contact System Administrator!"+error,
                        Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void openHomepage() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}