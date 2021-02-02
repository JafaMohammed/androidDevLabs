package com.mojaafar.mydroidcafev1;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mojaafar.mydroidcafev1.R;
import com.mojaafar.mydroidcafev1.StoresAdapter;
import com.mojaafar.mydroidcafev1.recipe;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StoresFragment extends Fragment {


    private RecyclerView storeRecyclerView;
    private ArrayList<recipe> storeRecipeData;
    private StoresAdapter storeRecipeAdapter;

    public StoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stores, container, false);

        storeRecyclerView = rootView.findViewById(R.id.recycler_store);
        // Set LayoutManager for the RecyclerView
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize ArrayList
        storeRecipeData = new ArrayList<>();
        // Initialize RecipeAdapter
        storeRecipeAdapter = new StoresAdapter(storeRecipeData, getContext());
        // Set adapter
        storeRecyclerView.setAdapter(storeRecipeAdapter);

        initializeData();

        return rootView;
    }

    private void initializeData() {
        String[] storeLocations = getResources().getStringArray(R.array.store_location);
        String[] storeDescriptions = getResources().getStringArray(R.array.stores_description);
        TypedArray storeImages = getResources().obtainTypedArray(R.array.stores_images);

        // Clear existing data to avoid duplication
        storeRecipeData.clear();

        // Create and ArrayList of dessert recipes
        for (int i = 0; i < storeLocations.length; i++) {
            storeRecipeData.add(new recipe(
                    storeImages.getResourceId(i, 0),
                    storeLocations[i],
                    storeDescriptions[i]));
        }
        // Clean up data in the TypedArray
        storeImages.recycle();
        // Notify the adapter of the change in the data set
        storeRecipeAdapter.notifyDataSetChanged();
    }

}
