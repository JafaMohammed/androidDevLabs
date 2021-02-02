package com.mojaafar.mydroidcafev1;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastriesRecipeFragment extends Fragment {

    private RecyclerView pastryRecyclerView;
    private ArrayList<recipe> pastryRecipeData;
    private recipeAdapter pastryRecipeAdapter;

    public PastriesRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pastries_recipe, container, false);

        pastryRecyclerView = rootView.findViewById(R.id.recycler_pastry);
        // Set LayoutManager for the RecyclerView
        pastryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize ArrayList
        pastryRecipeData = new ArrayList<>();
        // Initialize RecipeAdapter
        pastryRecipeAdapter = new recipeAdapter(pastryRecipeData, getContext());
        // Set adapter
        pastryRecyclerView.setAdapter(pastryRecipeAdapter);

        initializeData();

        // Implement swiping
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(pastryRecipeData, from, to);
                pastryRecipeAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pastryRecipeData.remove(viewHolder.getAdapterPosition());
                pastryRecipeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(pastryRecyclerView);
        return rootView;
    }

    private void initializeData() {
        String[] pastryTitles = getResources().getStringArray(R.array.pastry_titles);
        String[] pastryDescriptions = getResources().getStringArray(R.array.pastry_descriptions);
        TypedArray pastryImages = getResources().obtainTypedArray(R.array.pastry_images);

        // Clear existing data to avoid duplication
        pastryRecipeData.clear();

        // Create and ArrayList of dessert recipes
        for (int i = 0; i < pastryTitles.length; i++) {
            pastryRecipeData.add(new recipe(
                    pastryImages.getResourceId(i, 0),
                    pastryTitles[i],
                    pastryDescriptions[i]));
        }
        // Clean up data in the TypedArray
        pastryImages.recycle();
        // Notify the adapter of the change in the data set
        pastryRecipeAdapter.notifyDataSetChanged();
    }

}
