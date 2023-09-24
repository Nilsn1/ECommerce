package com.nilscreation.ecommerce.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.nilscreation.ecommerce.R;
import com.nilscreation.ecommerce.adapter.ProductAdapter;
import com.nilscreation.ecommerce.model.ProductDetails;
import com.nilscreation.ecommerce.service.ApiService;
import com.nilscreation.ecommerce.service.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    RecyclerView recyclerview;
    ProductAdapter adapter;
    List<ProductDetails> productList;
    List<ProductDetails> filterList;
    SearchView searchView;
    Spinner sortSpinner;
    private ArrayList<String> categorylist;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sortSpinner = view.findViewById(R.id.sortSpinner);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<ProductDetails>> call = apiService.getProducts();

        productList = new ArrayList<>();
        filterList = new ArrayList<>();
        categorylist = new ArrayList<>();

        categorylist.add("All");
        categorylist.add("electronics");
        categorylist.add("jewelery");
        categorylist.add("men's clothing");
        categorylist.add("women's clothing");
        categorylist.add("Low to High");
        categorylist.add("High to Low");

        call.enqueue(new Callback<List<ProductDetails>>() {
            @Override
            public void onResponse(Call<List<ProductDetails>> call, Response<List<ProductDetails>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    adapter = new ProductAdapter(getContext(), productList);
                    recyclerview.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "Error:" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductDetails>> call, Throwable t) {
                Toast.makeText(getContext(), "Error:" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = categorylist.get(position);
                filterList.clear();

                for (ProductDetails productDetails : productList) {

                    if (category.equals("All")) {
                        filterList.add(productDetails);
                    } else if (category.equals("Low to High")) {
                        filterList.add(productDetails);
                        Collections.sort(filterList, new Comparator<ProductDetails>() {
                            @Override
                            public int compare(ProductDetails p1, ProductDetails p2) {
                                return Double.compare(p1.getPrice(), p2.getPrice());
                            }
                        });
                    } else if (category.equals("High to Low")) {
                        filterList.add(productDetails);
                        Collections.sort(filterList, new Comparator<ProductDetails>() {
                            @Override
                            public int compare(ProductDetails p1, ProductDetails p2) {
                                return Double.compare(p2.getPrice(), p1.getPrice());
                            }
                        });
                    } else if (productDetails.getCategory().equals(category.toLowerCase())) {
                        filterList.add(productDetails);
                    }
                }
                adapter = new ProductAdapter(getContext(), filterList);
                recyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categorylist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);


        return view;
    }

    private void filterData(String query) {
        filterList.clear();
        for (ProductDetails productDetails : productList) {
            if (productDetails.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filterList.add(productDetails);
            }
        }
        adapter = new ProductAdapter(getContext(), filterList);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}