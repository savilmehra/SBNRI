package com.in.sbnri.retrofit;


import com.in.sbnri.entities.MainEntity;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Webservices {
    @GET("orgs/octokit/repos")
    Call<List<MainEntity>> getData(@Query("page")  int page, @Query("per_page") int perPage);

}

