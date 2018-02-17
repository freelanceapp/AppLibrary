package com.semicolon.librarians.library.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.semicolon.librarians.library.Adapters.CountryAdapter;
import com.semicolon.librarians.library.MVP.CountriesMVP.Presenter;
import com.semicolon.librarians.library.MVP.CountriesMVP.PresenterImp;
import com.semicolon.librarians.library.MVP.CountriesMVP.ViewData;
import com.semicolon.librarians.library.Models.CountriesModel;
import com.semicolon.librarians.library.R;
import com.semicolon.librarians.library.Services.Tags;

import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by Delta on 28/01/2018.
 */

public class Fragment_Countries extends Fragment implements ViewData{

    private ProgressBar progressBar ;
    private RecyclerView mRecView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private Presenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(), Tags.font,true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countries,container,false);

        initView(view);
        presenter = new PresenterImp(this,getActivity());
        return view;
    }


    private void initView(View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.country_progressBar);
        mRecView = (RecyclerView) view.findViewById(R.id.recView_countries);
        mRecView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        mRecView.setLayoutManager(manager);


    }

    @Override
    public void onCountryDataSuccess(List<CountriesModel> countriesModelList) {
        adapter = new CountryAdapter(countriesModelList,getActivity());
        adapter.notifyDataSetChanged();
        mRecView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onCountryDataFailed(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getCountryData();
    }
}