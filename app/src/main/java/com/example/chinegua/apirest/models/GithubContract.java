package com.example.chinegua.apirest.models;

import android.provider.BaseColumns;

/**
 * Created by chinegua on 19/10/17.
 */

final public class GithubContract {

    private GithubContract() {}

    public static abstract class tablaGithub implements BaseColumns{
        static final String TABLE_NAME = "gitHubUser";
        static final String COL_NAME_ID       = _ID;
        static final String COL_USERNAME = "username";
        static final String COL_AVATAR = "avatar";
        static final String COL_REPOS = "repos";
        static final String COL_REPOS_NUMBER = "reposNumber";
        static final String COL_FOLLOWER = "follower";
        static final String COL_FOLLOWING = "following";







    }

}