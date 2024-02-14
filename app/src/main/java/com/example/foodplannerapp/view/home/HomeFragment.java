package com.example.foodplannerapp.view.home;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannerapp.R;
import com.example.foodplannerapp.model.Recipe;
import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
import com.example.foodplannerapp.presenter.RecipePresenter;
import com.example.foodplannerapp.presenter.RecipePresenterImp;

import java.util.List;


public class HomeFragment extends Fragment implements AllRecipeView {
 RecyclerView recyclerView;
 LinearLayoutManager layoutManager;
 MyAdapter myAdapter;
    View view;
    ImageView imageViewSingleMeal;
    TextView foodSingleName;
    RecipePresenter recipePresenter;
 // localData


    List<Recipe> recipe;
private Context context;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
         imageViewSingleMeal = view.findViewById(R.id.image_thum);
         foodSingleName = view.findViewById(R.id.food_name);
         recipePresenter = new RecipePresenterImp(this , new RecipeRemoteDataSourceImp());
         recipePresenter.getRecipe();
//            TextView plane_btn = view.findViewById(R.id.plane_btn);
//            ImageButton fav_btn = view.findViewById(R.id.fav_btn);

        return view;
    }

    @Override
    public void showData(Recipe recipes) {
        Glide.with(imageViewSingleMeal.getContext()).load(recipes.getStrMealThumb()).into(imageViewSingleMeal);
        foodSingleName.setText(recipes.getStrMeal());


        }


    @Override
    public void showErrMSG(String error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An Error Occured");
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}