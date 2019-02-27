package com.spchtest.myapplication.viewmodels;

import android.app.Application;
import android.util.Log;

import com.spchtest.myapplication.models.CallData;
import com.spchtest.myapplication.models.Record;
import com.spchtest.myapplication.retrofitapi.countryApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryViewModel extends AndroidViewModel {

    private MutableLiveData<CallData> callDataList;

    public LiveData<CallData> getCallDataList(){
        if(callDataList == null){
            callDataList = new MutableLiveData<CallData>();
            loadCallData();
        }
        return callDataList;
    }

    private void loadCallData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(countryApi.BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        countryApi api =  retrofit.create(countryApi.class);
        Call<CallData> call = api.getCallData("a807b7ab-6cad-4aa6-87d0-e283a7353a0f");

        call.enqueue(new Callback<CallData>() {
            @Override
            public void onResponse(Call<CallData> call, Response<CallData> response) {
                CallData res = response.body();
                callDataList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CallData> call, Throwable t) {
                Log.d("Error",t.getMessage().toString());
            }
        });

    }



    public CountryViewModel(@NonNull Application application) {
        super(application);
    }
}
