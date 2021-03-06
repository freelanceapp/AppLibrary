package com.semicolon.librarians.libraryguide.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.semicolon.librarians.libraryguide.Adapters.Library_Search_Adapter;
import com.semicolon.librarians.libraryguide.MVP.Search_Library_MVP.Presenter;
import com.semicolon.librarians.libraryguide.MVP.Search_Library_MVP.PresenterImp;
import com.semicolon.librarians.libraryguide.MVP.Search_Library_MVP.ViewData;
import com.semicolon.librarians.libraryguide.Models.LibraryModel;
import com.semicolon.librarians.libraryguide.R;
import com.semicolon.librarians.libraryguide.Services.Tags;

import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by Delta on 29/01/2018.
 */

public class Fragment_Library_Search_Results extends Fragment implements ViewData{
    private ProgressBar progressBar ;
    private RecyclerView mRecView;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.Adapter adapter;
    private Bundle bundle;
    private String libName,lib_type,country_id;
    private Presenter presenter;
    private LinearLayout error_container,noresult_container;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(), Tags.font,true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_search_results,container,false);
        initView(view);

        presenter = new PresenterImp(this,getActivity());
        getDataFromBundle();
        return view;
    }

    private void getDataFromBundle() {

        bundle = getArguments();
        if (bundle!=null)
        {
            libName    = bundle.getString("libraryName");
            lib_type   = bundle.getString("libraryType");
            country_id = bundle.getString("country_id");
            //service_id = bundle.getString("service_id");
            Log.e("Data2",libName+"\n"+country_id+"\n"+"\n"+lib_type);
            presenter.getLibraryData(libName,lib_type,country_id);


        }
    }

    private void initView(View view) {
        error_container = (LinearLayout) view.findViewById(R.id.error_container);
        noresult_container = (LinearLayout) view.findViewById(R.id.noresult_container);

        progressBar = (ProgressBar) view.findViewById(R.id.lib_search_results_progressBar);
        mRecView = (RecyclerView) view.findViewById(R.id.recView_lib_search_results);
        mRecView.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        mRecView.setLayoutManager(manager);

    }


    @Override
    public void onLibraryDataSuccess(List<LibraryModel> libraryModelList) {
        if (libraryModelList.size()>0)
        {
            adapter = new Library_Search_Adapter(libraryModelList,getActivity());
            adapter.notifyDataSetChanged();
            mRecView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
            mRecView.setVisibility(View.VISIBLE);
            noresult_container.setVisibility(View.GONE);

        }else
            {
                noresult_container.setVisibility(View.VISIBLE);
            }


    }

    @Override
    public void onLibraryDataFailed(String error) {
        progressBar.setVisibility(View.GONE);
        error_container.setVisibility(View.VISIBLE);
        mRecView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }


}
