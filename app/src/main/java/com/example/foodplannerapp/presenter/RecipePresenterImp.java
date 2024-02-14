package com.example.foodplannerapp.presenter;

import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.model.RecipeReposatory;
import com.example.foodplannerapp.network.NetworkCallBack;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.view.home.AllRecipeView;

import java.util.List;

public class RecipePresenterImp implements RecipePresenter , NetworkCallBack {
    private AllRecipeView _view;
    private RecipeReposatory _repo;
    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;

    public RecipePresenterImp(AllRecipeView _view, RecipeRemoteDataSourceImp _repository) {
        this._view = _view;
        this.recipeRemoteDataSourceImp = _repository;
    }
    @Override
    public void getRecipe() {
        recipeRemoteDataSourceImp.makeNetworkCall(this);
    }

    @Override
    public void getCategory() {

    }

    @Override
    public void onSucessfull(List<Recipe> recipe) {
            _view.showData(recipe.get(0));

    }

    @Override
    public void onFileur(String errMsg) {
          _view.showErrMSG(errMsg);
    }


}
