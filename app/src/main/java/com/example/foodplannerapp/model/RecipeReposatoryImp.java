package com.example.foodplannerapp.model;

import com.example.foodplannerapp.network.NetworkCallBack;
import com.example.foodplannerapp.network.RecipeRemoteDataSource;

public class RecipeReposatoryImp implements  RecipeReposatory{

    RecipeRemoteDataSource recipeRemoteDataSource;
    private static RecipeReposatoryImp repo =null;

    public RecipeReposatoryImp(RecipeRemoteDataSource recipeRemoteDataSource) {
        this.recipeRemoteDataSource = recipeRemoteDataSource;
    }

    public static RecipeReposatoryImp getInstance(RecipeRemoteDataSource remoteSource ){
        if(repo == null){
            repo = new RecipeReposatoryImp(remoteSource);
        }
        return repo;
    }

    @Override
    public void getAllRecipes(NetworkCallBack networkCallBack) {
        recipeRemoteDataSource.makeNetworkCall(networkCallBack);
    }
}
