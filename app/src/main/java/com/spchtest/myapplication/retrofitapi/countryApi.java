package com.spchtest.myapplication.retrofitapi;

import com.spchtest.myapplication.models.CallData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface countryApi {

    String BASE_URI = "https://data.gov.sg/api/action/";

    @GET("datastore_search")
    Call<CallData> getCallData(@Query("resource_id") String resource);

}
