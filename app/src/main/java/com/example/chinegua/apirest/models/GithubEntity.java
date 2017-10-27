package com.example.chinegua.apirest.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GithubEntity implements Parcelable {
    String user;
    String avatar;
    String repos;
    int reposNumber;
    int follower;
    int following;

    protected GithubEntity(Parcel in) {
        user = in.readString();
        avatar = in.readString();
        repos = in.readString();
        reposNumber = in.readInt();
        follower = in.readInt();
        following = in.readInt();
    }

    public GithubEntity(String user, String avatar, String repos, int reposNumber, int follower, int following) {
        this.user = user;
        this.avatar = avatar;
        this.repos = repos;
        this.reposNumber = reposNumber;
        this.follower = follower;
        this.following = following;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public int getReposNumber() {
        return reposNumber;
    }

    public void setReposNumber(int reposNumber) {
        this.reposNumber = reposNumber;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user);
        dest.writeString(avatar);
        dest.writeString(repos);
        dest.writeInt(reposNumber);
        dest.writeInt(follower);
        dest.writeInt(following);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GithubEntity> CREATOR = new Parcelable.Creator<GithubEntity>() {
        @Override
        public GithubEntity createFromParcel(Parcel in) {
            return new GithubEntity(in);
        }

        @Override
        public GithubEntity[] newArray(int size) {
            return new GithubEntity[size];
        }
    };
}