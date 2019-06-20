package com.nytsample.activites;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nytsample.R;
import com.nytsample.adapters.NewsListAdapter;
import com.nytsample.listeners.APIListner;
import com.nytsample.model.MostPopularResponse;
import com.nytsample.model.Results;
import com.nytsample.network.NetworkDetector;
import com.nytsample.utility.Constants;
import com.nytsample.utility.Display;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsListActivity extends AppCompatActivity {

    private RecyclerView mNewsListRV;
    private ProgressBar mProgressBar;
    Context cntx;
    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        cntx = this;
        initViews();

        if(new NetworkDetector(this).isConnectingToInternet()){
            apiCall("all-sections", "7");//passing section, period
        } else {
            Display.DisplayToastMust(this, "No network connection.");
        }
    }

    private void initViews(){
        mNewsListRV = findViewById(R.id.news_list_rv);
        mProgressBar = findViewById(R.id.progressBar);

    }

    private void apiCall(String section, String period) {
        APIListner apiService = getResponse().create(APIListner.class);
        Call<MostPopularResponse> call = apiService.getNewsDetails(section, period, Constants.API_KEY);
        call.enqueue(new Callback<MostPopularResponse>() {
            @Override
            public void onResponse(Call<MostPopularResponse> call, Response<MostPopularResponse> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    List<Results> newsList = response.body().getResults();
                    Display.DisplayLogD("RSA", " Response : "+newsList.toString());
                    callRecycler(newsList);
                } else {
                    Display.DisplayLogD("RSA","error response");
                }
            }

            @Override
            public void onFailure(Call<MostPopularResponse> call, Throwable t) {
                Display.DisplayLogD("RSA",t.getMessage());
            }
        });
    }

    public static Retrofit getResponse() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private void callRecycler(List<Results> newsList) {
        mNewsListRV.setLayoutManager(new LinearLayoutManager(this));
        mNewsListRV.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mNewsListRV.setAdapter(new NewsListAdapter(this, newsList));

        assert newsList != null;
        if (newsList.size() > 0) {
            mProgressBar.setVisibility(View.GONE);
            mNewsListRV.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mNewsListRV.setVisibility(View.GONE);
        }
    }
}
