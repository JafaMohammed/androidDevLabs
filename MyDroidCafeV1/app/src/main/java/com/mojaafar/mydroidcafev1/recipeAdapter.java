package com.mojaafar.mydroidcafev1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.ViewHolder> {

    // Member variables for recipe data and context
    private ArrayList<recipe> mRecipes;
    private Context mContext;

    // Constructor to pass recipe data and context
    public recipeAdapter (ArrayList<recipe> recipes, Context context) {
        this.mContext = context;
        this.mRecipes = recipes;
    }

    @NonNull
    @Override
    public recipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recipe_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull recipeAdapter.ViewHolder holder, int position) {
        // Get current view object using its position and populate it with data
        recipe currentRecipe = mRecipes.get(position);
        holder.bindTo(currentRecipe);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mRecipeImage;
        private TextView mRecipeTitle;
        private TextView mRecipeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeImage = itemView.findViewById(R.id.recipe_image);
            mRecipeTitle = itemView.findViewById(R.id.recipe_title);
            mRecipeDescription = itemView.findViewById(R.id.recipe_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dessertPosition = getAdapterPosition();
                    recipe currentrecipe = mRecipes.get(dessertPosition);
                    Intent donutIntent = new Intent(mContext, DonutActivity.class);
                    donutIntent.putExtra("dTitle", currentrecipe.getRecipeTitle());
                    donutIntent.putExtra("dImage", currentrecipe.getRecipeImage());
                    donutIntent.putExtra("dDescription", currentrecipe.getRecipeDescription());
                    mContext.startActivity(donutIntent);
                }
            });
        }

        /**
         * A method to bind the current view with the data
         * @param currentRecipe
         */
        public void bindTo(recipe currentRecipe) {
            // Use Glide library to load images to prevent crashing
            Glide.with(mContext)
                    .load(currentRecipe.getRecipeImage())
                    .into(mRecipeImage);

            mRecipeTitle.setText(currentRecipe.getRecipeTitle());
            mRecipeDescription.setText(currentRecipe.getRecipeDescription());
        }
    }
}
