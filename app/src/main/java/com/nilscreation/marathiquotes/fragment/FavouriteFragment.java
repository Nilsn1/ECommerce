package com.nilscreation.marathiquotes.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.rvadapter.AdmobNativeAdAdapter;
import com.nilscreation.marathiquotes.adapter.FavouriteAdapter;
import com.nilscreation.marathiquotes.service.MyDBHelper;
import com.nilscreation.marathiquotes.R;
import com.nilscreation.marathiquotes.model.QuoteModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<QuoteModel> factslist;
    Context context;

    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        factslist = new ArrayList<>();

        fetchData();

        return view;
    }

    private void fetchData() {
        MyDBHelper myDBHelper = new MyDBHelper(getContext());

        ArrayList<QuoteModel> facts = myDBHelper.readData();

        for (int i = 0; i < facts.size(); i++) {

            String title = facts.get(i).getTitle();
            String category = facts.get(i).getTitle();

            QuoteModel factmodel = new QuoteModel(title, category);
            factslist.add(factmodel);

            FavouriteAdapter adapter = new FavouriteAdapter(context, factslist, getActivity());
//                    recyclerView.setAdapter(adapter);
            AdmobNativeAdAdapter admobNativeAdAdapter = AdmobNativeAdAdapter.Builder.with("ca-app-pub-9137303962163689/3884272678", adapter,
                    "small").adItemInterval(3).build();
            recyclerView.setAdapter(admobNativeAdAdapter);

        }
    }
}