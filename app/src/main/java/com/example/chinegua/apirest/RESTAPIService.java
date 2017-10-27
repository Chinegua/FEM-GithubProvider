package com.example.chinegua.apirest;

        import com.example.chinegua.apirest.models.Github;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;


@SuppressWarnings("Unused")
interface RESTAPIService {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    @GET("/users/{userName}")
    Call<Github> getGithubUser(@Path("userName") String userName);


}
