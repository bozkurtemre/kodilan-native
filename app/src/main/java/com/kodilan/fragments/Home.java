package com.kodilan.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kodilan.R;
import com.kodilan.adapters.RecentJobsAdapter;
import com.kodilan.models.Data;
import com.kodilan.requests.getRecentJobs;
import com.kodilan.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    private RecyclerView homeRv;
    private ArrayList<Data> dataList;
    private RecentJobsAdapter recentJobsAdapter;

    private SwipeRefreshLayout swipeLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeLayout = view.findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecentJobs();
            }
        });

        homeRv = view.findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        homeRv.setNestedScrollingEnabled(false);
        homeRv.setHasFixedSize(true);
        homeRv.setItemViewCacheSize(25);

        getRecentJobs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void getRecentJobs() {
        Call<getRecentJobs> call = RetrofitClient
                .getInstance().getApi().getRecentJobs(25, "monthly");
        try {
            call.enqueue(new Callback<getRecentJobs>() {
                @Override
                public void onResponse(Call<getRecentJobs> call, Response<getRecentJobs> response) {
                    if (response.isSuccessful()) {
                        dataList = response.body().getData();
                        recentJobsAdapter = new RecentJobsAdapter(getActivity(), dataList);
                        homeRv.setAdapter(recentJobsAdapter);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                recentJobsAdapter.showShimmer = false;
                                recentJobsAdapter.notifyDataSetChanged();
                                swipeLayout.setRefreshing(false);
                            }
                        }, 1000);
                    } else {
                        Toast.makeText(getActivity(), "Request Fetch Hatası!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<getRecentJobs> call, Throwable t) {
                    Toast.makeText(getActivity(), "Request Hatası: " + t, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Request Hatası: " + e, Toast.LENGTH_LONG).show();
        }
    }
}