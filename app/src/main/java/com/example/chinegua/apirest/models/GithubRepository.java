package com.example.chinegua.apirest.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public long add(String name,String avatar,String repos,int reposNumber,int follower, int following){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(GithubContract.tablaGithub.COL_USERNAME,name);
        content.put(GithubContract.tablaGithub.COL_AVATAR,avatar);
        content.put(GithubContract.tablaGithub.COL_REPOS,repos);
        content.put(GithubContract.tablaGithub.COL_REPOS_NUMBER,reposNumber);
        content.put(GithubContract.tablaGithub.COL_FOLLOWER,follower);
        content.put(GithubContract.tablaGithub.COL_FOLLOWING,following);

        return db.insert(GithubContract.tablaGithub.TABLE_NAME, null, content);

    }

    public boolean checkIfExist(String name){
        try {
            String query = "Select * from " + GithubContract.tablaGithub.TABLE_NAME + " where " + GithubContract.tablaGithub.COL_USERNAME
                    + " = '" + name + "';";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            GithubEntity user = new GithubEntity(
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_AVATAR)),
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_REPOS)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_REPOS_NUMBER)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_FOLLOWER)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_FOLLOWING))
            );
            if (name.equals(user.getUser())) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e){
            return false;
        }

    }

    public GithubEntity getUser(String name){
        try {
            String query = "Select * from " + GithubContract.tablaGithub.TABLE_NAME + " where " + GithubContract.tablaGithub.COL_USERNAME
                    + " = '" + name + "';";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            GithubEntity user = new GithubEntity(
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_AVATAR)),
                    cursor.getString(cursor.getColumnIndex(GithubContract.tablaGithub.COL_REPOS)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_REPOS_NUMBER)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_FOLLOWER)),
                    cursor.getInt(cursor.getColumnIndex(GithubContract.tablaGithub.COL_FOLLOWING))
            );

            return user;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
