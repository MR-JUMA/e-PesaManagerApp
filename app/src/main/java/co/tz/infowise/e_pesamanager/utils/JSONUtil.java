package co.tz.infowise.e_pesamanager.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import co.tz.infowise.e_pesamanager.models.TigoTransactions;
import co.tz.infowise.e_pesamanager.models.Token;

public class JSONUtil {

    public static Token loadUserFromJson(JSONObject jsonObject) throws JSONException {
//        JSONObject data = new JSONObject(jsonObject.getString(""));
        Token token = new Token();
        token.setToken(jsonObject.getString("token"));
        return token;
    }

    public static ArrayList<TigoTransactions> loadTigoTransactionsFromJson(JSONArray jsonArray) throws JSONException {
    ArrayList<TigoTransactions> transactions = new ArrayList<>();
//        JSONArray jsonArray = new JSONArray(jsonObject.getString(""));
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            TigoTransactions tigoTransactions=new TigoTransactions();
            tigoTransactions.setId(data.getLong("transactionID"));
            tigoTransactions.setTransactionType(data.getString("transactionType"));
            tigoTransactions.setAmount(data.getString("amount"));
            tigoTransactions.setPhoneNumber(data.getString("phonenumber"));
            tigoTransactions.setStatus(data.getString("status"));

            transactions.add(tigoTransactions);
        }
        return  transactions;
    }

//    public static ArrayList<TigoTransactions> loadSalesOrdersFromJSON(String jsonString) throws IOException, JSONException {
//        ArrayList<TigoTransactions>  tigoTransactions = new ArrayList();
//        JSONObject reader = new JSONObject(jsonString);
//        JSONArray data = reader.getJSONArray();
//        for (int i = 0; i < data.length(); i++){
//            JSONObject s = data.getJSONObject(i);
//            SaleOrder saleOrder = new SaleOrder();
//            saleOrder.setId(s.getLong("id"));
//            saleOrder.setOrderNumber(s.getString("orderNumber"));
//            saleOrder.setRefNumber(s.getString("refNumber"));
//            saleOrder.setOrderTotal(s.getString("orderTotal"));
//            saleOrder.setSupplierName(s.getString("supplierName"));
//            saleOrder.setDate(s.getString("date"));
//            saleOrder.setStatus(s.getString("status"));
//            saleOrder.setItems(s.getString("items"));
//            saleOrder.setPartnerName(s.getString("partnerName"));
//            orders.add(saleOrder);
//        }
//        return orders;
//    }


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
