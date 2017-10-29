package com.example.chinegua.apirest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.chinegua.apirest.models.GithubContract;
import com.example.chinegua.apirest.models.GithubRepository;

/**
 * Created by chinegua on 29/10/17.
 */

public class GithubProvider extends ContentProvider {
    private static final String AUTHORITY = GithubProvider.class.getPackage().getName() + ".provider";
    private static final String ENTITY = "github";
    private static final String uri = "content://" + AUTHORITY + "/" + ENTITY;
    private static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int ID_URI_USUARIO  = 1;
    private static final UriMatcher uriMatcher;

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTITY + "/*", ID_URI_USUARIO);
    }

    private GithubRepository githubRepository;


    @Override
    public boolean onCreate() {

        githubRepository = new GithubRepository(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        String where = "";
        switch (uriMatcher.match(uri)) {
            case ID_URI_USUARIO:  // URI termina en /#
                where = GithubContract.tablaGithub.COL_USERNAME + " = ?";
                selectionArgs = new String[1];
                selectionArgs[0] = uri.getLastPathSegment();
                break;
        }

        SQLiteDatabase db = githubRepository.getReadableDatabase();
        Cursor cursor = db.query(
                GithubContract.tablaGithub.TABLE_NAME,
                projection,
                where,
                selectionArgs,
                null, null, sortOrder);

        return cursor;
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
