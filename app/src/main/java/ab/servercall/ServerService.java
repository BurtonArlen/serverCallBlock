package ab.servercall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by arlen on 8/25/17.
 */

public class ServerService {
    public static void getUsers(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SERVER_PATH).newBuilder();
        urlBuilder.addPathSegment(Constants.USER_ROUTE);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<User> processResults(Response response){
        ArrayList<User> users = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()){
                JSONArray resultsJSON = new JSONArray(jsonData);
                for (int i = 0; i < resultsJSON.length(); i++){
                    JSONObject userJSON = resultsJSON.getJSONObject(i);
                    String firstName = userJSON.getString("first_name");
                    String lastName = userJSON.getString("last_name");
                    String email = userJSON.getString("email");
                    String id = userJSON.getString("id");
                    String status = userJSON.getString("status");
                    User user = new User(firstName, lastName, email, id, status);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
