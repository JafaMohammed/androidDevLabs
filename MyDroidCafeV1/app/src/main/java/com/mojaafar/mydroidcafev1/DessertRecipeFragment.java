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
import java.util.Collection;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class DessertRecipeFragment extends Fragment {

    private RecyclerView dessertRecyclerView;
    private ArrayList<recipe> dessertRecipeData;
    private recipeAdapter dessertRecipeAdapter;

    public DessertRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dessert_recipe, container, false);

        dessertRecyclerView = rootView.findViewById(R.id.recycler_dessert);
        // Set LayoutManager for the RecyclerView
        dessertRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize ArrayList
        dessertRecipeData = new ArrayList<>();
        // Initialize RecipeAdapter
        dessertRecipeAdapter = new recipeAdapter(dessertRecipeData, getContext());
        // Set adapter
        dessertRecyclerView.setAdapter(dessertRecipeAdapter);

        initializeData();

        // Implement swiping
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(dessertRecipeData, from, to);
                dessertRecipeAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dessertRecipeData.remove(viewHolder.getAdapterPosition());
                dessertRecipeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(dessertRecyclerView);
        return rootView;
    }

    private void initializeData() {
        String[] dessertTitles = getResources().getStringArray(R.array.dessert_title);
        String[] dessertDescriptions = getResources().getStringArray(R.array.dessert_description);
        TypedArray dessertImages = getResources().obtainTypedArray(R.array.desserts_images);

        // Clear existing data to avoid duplication
        dessertRecipeData.clear();

        // Create and ArrayList of dessert recipes
        for (int i = 0; i < dessertTitles.length; i++) {
            dessertRecipeData.add(new recipe(
                    dessertImages.getResourceId(i, 0),
                    dessertTitles[i],
                    dessertDescriptions[i]));
        }
        // Clean up data in the TypedArray
        dessertImages.recycle();
        // Notify the adapter of the change in the data set
        dessertRecipeAdapter.notifyDataSetChanged();
    }
}
