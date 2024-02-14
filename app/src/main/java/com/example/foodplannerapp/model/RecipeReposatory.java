package com.example.foodplannerapp.model;

import com.example.foodplannerapp.network.NetworkCallBack;

public interface RecipeReposatory {

    public void getAllRecipes(NetworkCallBack networkCallBack);

}
