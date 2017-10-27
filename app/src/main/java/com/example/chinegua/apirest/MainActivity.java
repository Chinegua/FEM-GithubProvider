package com.example.chinegua.apirest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chinegua.apirest.models.Github;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private final String URL_BASE = "https://api.github.com";
    private RESTAPIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RESTAPIService.class); //---------
    }

    public void obtenerUserInfo(View v){
        Log.i("MiW", "obtenerUserInfo");

        TextView userTV = (TextView) findViewById(R.id.searchUser);
        String userInput = userTV.getText().toString();


        Call<Github> call_async = apiService.getGithubUser(userInput);

        call_async.enqueue(new Callback<Github>() {
            @Override
            public void onResponse(Call<Github> call, Response<Github> response) {
                Github githubUser = response.body();
                if (null != githubUser) {
                    /*for (Github country : countryList) {
                        tvRespuesta.append(country.toString() + "\n\n");
                    }*/
                    Log.i("MiW", "obtenerInfoPais => respuesta=" + githubUser.toString());
            }

        }

            @Override
            public void onFailure(Call<Github> call, Throwable t) {
                Toast.makeText(
                        getApplicationContext(),
                        "ERROR: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                Log.e("MiW", t.getMessage());
            }
            });
    }
}
