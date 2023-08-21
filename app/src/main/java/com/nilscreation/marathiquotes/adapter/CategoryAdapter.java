package com.nilscreation.marathiquotes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nilscreation.marathiquotes.R;
import com.nilscreation.marathiquotes.fragment.MainFragment;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    ArrayList<String> categorylist;
    FragmentActivity activity;

    public CategoryAdapter(Context context, ArrayList<String> categorylist, FragmentActivity activity) {
        this.context = context;
        this.categorylist = categorylist;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        String category = categorylist.get(position);

        holder.txtCategory.setText(category);

        holder.txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("Category", category);

                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                MainFragment mainFragment = new MainFragment();
                mainFragment.setArguments(bundle);
                transaction.replace(R.id.mainContainer, mainFragment);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;
        LinearLayout layoutCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            layoutCategory = itemView.findViewById(R.id.layoutCategory);
        }
    }

}
