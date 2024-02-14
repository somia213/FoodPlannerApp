package com.example.foodplannerapp.network;

import com.example.foodplannerapp.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {
    @GET("random.php")
    Call<RecipeResponse> getRecipe();

    @GET("categories.php")
    Call<RecipeResponse> getCategory();
}
