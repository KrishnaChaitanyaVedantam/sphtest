package com.spchtest.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spchtest.myapplication.adapters.ResponseAdapter;
import com.spchtest.myapplication.models.CallData;
import com.spchtest.myapplication.viewmodels.CountryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel countryListViewModel;
    private RecyclerView rvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvResponse = findViewById(R.id.rlvData);
        rvResponse.setLayoutManager(new LinearLayoutManager(this));
        rvResponse.setHasFixedSize(true);

        final ResponseAdapter adapter = new ResponseAdapter();
        rvResponse.setAdapter(adapter);

        countryListViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);
        countryListViewModel.getCallDataList().observe(this, new Observer<CallData>() {
            @Override
            public void onChanged(CallData callData) {
                adapter.setCallData(callData);
            }
        });
    }
}
