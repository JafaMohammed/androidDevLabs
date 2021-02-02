package com.mojaafar.mydroidcafev1;

public class recipe {
    //declare private member variables
    private final int recipeImage;
    private String recipeTitle;
    private String recipeDescription;

    /*
    Create a constructor for the recipe data model
    Pass the parameters recipeImage, recipeTitle, recipeDescription
     */

    public recipe(int recipeImage, String recipeTitle, String recipeDescription) {
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }
}
