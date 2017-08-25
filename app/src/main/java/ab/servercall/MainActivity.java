package ab.servercall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.button1) Button mButton1;
    public ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mButton1.setOnClickListener(this);
    }
    private void getUsers() {
        final ServerService serverService = new ServerService();
        serverService.getUsers(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mUsers = serverService.processResults(response);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] userNames = new String[mUsers.size()];
                        String[] userIds = new String[mUsers.size()];
                        String[] userEmail = new String[mUsers.size()];
                        String[] userStatus = new String[mUsers.size()];
                        for (int i = 0; i < userNames.length; i++) {
                            userNames[i] = mUsers.get(i).getFirstName() + " " + mUsers.get(i).getLastName();
                            userIds[i] = mUsers.get(i).getUserId();
                            userEmail[i] = mUsers.get(i).getUserEmail();
                            userStatus[i] = mUsers.get(i).getUserStatus();
                            Log.d("userName", userNames[i]);
                            Log.d("userId", userIds[i]);
                            Log.d("userEmail", userEmail[i]);
                            Log.d("userStatus", userStatus[i]);
                        }
//                        SearchedBeerListAdapter adapter = new SearchedBeerListAdapter(getApplicationContext(), mBeers);
//                        mSearchBrewList.setAdapter(adapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BrewSearch.this);
//                        mSearchBrewList.setLayoutManager(layoutManager);
//                        mSearchBrewList.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v){
        if (v == mButton1){
            getUsers();
        }
    }
}
