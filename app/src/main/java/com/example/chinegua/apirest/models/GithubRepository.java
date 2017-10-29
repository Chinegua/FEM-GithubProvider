package com.example.chinegua.apirest.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.chinegua.apirest.models.GithubContract;
/**
 * Created by chinegua on 29/10/17.
 */

public class GithubRepository extends SQLiteOpenHelper {

    private static final String DB_NAME = GithubContract.tablaGithub.TABLE_NAME + ".db";
    private static final int DB_VERSION = 1;

    public GithubRepository(Context contexto) {
        super(contexto, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + GithubContract.tablaGithub.TABLE_NAME+" ("
                + GithubContract.tablaGithub._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GithubContract.tablaGithub.COL_USERNAME  + " TEXT, "
                + GithubContract.tablaGithub.COL_AVATAR + " TEXT, "
                + GithubContract.tablaGithub.COL_REPOS + " TEXT, "
                + GithubContract.tablaGithub.COL_REPOS_NUMBER+ " INTEGER, "
                + GithubContract.tablaGithub.COL_FOLLOWER+ " INTEGER, "
                + GithubContract.tablaGithub.COL_FOLLOWING+ " INTEGER) ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
