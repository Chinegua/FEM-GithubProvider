package com.example.chinegua.apirest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.chinegua.apirest.models.Github;
import com.example.chinegua.apirest.models.GithubContract;
import com.example.chinegua.apirest.models.GithubRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chinegua on 29/10/17.
 */

public class GithubProvider extends ContentProvider {
    //content://com.example.chinegua.apirest.provider/user/cc
    private static final String AUTHORITY = "content://com.example.chinegua.apirest.provider";
    private static final String ENTITY = "user";
    private static final String uri = AUTHORITY+"/"+ENTITY;
    private static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int ID_URI_USUARIO  = 1;
    private static final UriMatcher uriMatcher;

    private final String URL_BASE = "https://api.github.com";
    private RESTAPIService apiService;
    GithubRepository db;




    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.chinegua.apirest.provider", "user/*", ID_URI_USUARIO);
    }

    private GithubRepository githubRepository;


    @Override
    public boolean onCreate() {

        githubRepository = new GithubRepository(getContext());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RESTAPIService.class);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.i("MiW","La peticion se ha realizado");
        String where = "";
        switch (uriMatcher.match(uri)) {
            case ID_URI_USUARIO:  // URI termina en /#

                where = GithubContract.tablaGithub.COL_USERNAME + " = ?";
                selectionArgs = new String[1];
                selectionArgs[0] = uri.getLastPathSegment();
                break;
        }

        if(!githubRepository.checkIfExist(selectionArgs[0].toString())){

            Log.i("MiW","selectionArgs >>>>>>>"+selectionArgs[0].toString());

            SQLiteDatabase db = githubRepository.getReadableDatabase();
            Cursor cursor = db.query(
                    GithubContract.tablaGithub.TABLE_NAME,
                    projection,
                    where,
                    selectionArgs,
                    null, null, sortOrder);

            return cursor;

        }
        else{
            Call<Github> call_async = apiService.getGithubUser(selectionArgs[0].toString());

            call_async.enqueue(new Callback<Github>() {
                @Override
                public void onResponse(Call<Github> call, Response<Github> response) {
                    Github githubUser = response.body();
                    db.add(githubUser.getLogin(),githubUser.getAvatarUrl(),githubUser.getReposUrl(),githubUser.getPublicRepos()
                    ,githubUser.getFollowers(),githubUser.getFollowing());




                }

                @Override
                public void onFailure(Call<Github> call, Throwable t) {
                    Log.i("MiW","Error al obtener los datos");
                }
            });

            SQLiteDatabase db = githubRepository.getReadableDatabase();
            Cursor cursor = db.query(
                    GithubContract.tablaGithub.TABLE_NAME,
                    projection,
                    where,
                    selectionArgs,
                    null, null, sortOrder);

            return cursor;
        }



    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case ID_URI_USUARIO:
                return "vnd.android.cursor.dir/vnd.miw.githubUser";    // lista de entidades
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
