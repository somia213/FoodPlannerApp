package com.example.foodplannerapp.network;

import android.util.Log;

import com.example.foodplannerapp.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRemoteDataSourceImp implements RecipeRemoteDataSource{

    public static final String TAG = "Network";
    private static final String URL = "https://www.themealdb.com/api/json/v1/1/";

    private static RecipeRemoteDataSourceImp client = null; // single ton
     RecipeService recipeService;

    public RecipeRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
                  recipeService = retrofit.create(RecipeService.class);
    }

//    public static RecipeRemoteDataSourceImp getInstance() {
//        if (client == null) {
//            client = new RecipeRemoteDataSourceImp();
//        }
//        return client;
//    }


    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
        Call<RecipeResponse> call = recipeService.getRecipe();
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {

                if (response.isSuccessful()) {
                    networkCallBack.onSucessfull(response.body().meals);
                    Log.i(TAG, "onResponse: " + response.body() + " recipe successfully downloaded");

                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                networkCallBack.onFileur(t.getMessage());
                Log.i(TAG, "onResponse: " + " data Field to downloaded");

            }
        });



//
//        Call<RecipeResponse> callCateg = recipeService.getCategory();
//        callCateg.enqueue(new Callback<RecipeResponse>() {
//            @Override
//            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
//                networkCallBack.onSucessfull(response.body().recipe);
//            }
//
//            @Override
//            public void onFailure(Call<RecipeResponse> call, Throwable t) {
//                 networkCallBack.onFileur(t.getMessage());
//            }
//        });
    }
}
