package com.example.demoexamwear.wear;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoexamwear.API.API;
import com.example.demoexamwear.API.MovieParam;
import com.example.demoexamwear.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity
{
    private RecyclerView recyclerView;
    private API api;
    private List<MovieParam> params;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RecyclerView);
        api = API.retrofit.create(API.class);
        params = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        Adapter adapter = new Adapter(params);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        func();
    }
    private void func()
    {
        Call<List<MovieParam>> call = api.getMovie();
        call.enqueue(new Callback<List<MovieParam>>() {
            @Override
            public void onResponse(Call<List<MovieParam>> call, Response<List<MovieParam>> response)
            {
                if(response.isSuccessful()) {
                    params.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MovieParam>> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}