package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class IngredientDetailActivity extends AppCompatActivity implements IngredientDetailRequest.Callback {

    String ingredient_name, message, ingredient_id;
    ImageLoader imageLoader;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();
        spinner = findViewById(R.id.progressBarDI);
        spinner.setVisibility(View.GONE);

        // show proper ingredient item
        Intent intent = getIntent();

        // request detailed ingredient information
        if (intent.getStringExtra("id_message") != null) {
            message = intent.getStringExtra("id_message");

            IngredientDetailRequest detailRequest = new IngredientDetailRequest(this, message);
            detailRequest.getIngredientDetails(this, message);
        }
        else {
            // get name of ingredient
            Ingredient ingredient = (Ingredient) intent.getSerializableExtra("ingredient");
            ingredient_name = ingredient.getName();

            // give it the proper data
            TextView named = findViewById(R.id.ingredient_detail_name);
            NetworkImageView imaged = findViewById(R.id.ingredient_detail_image);

            // set name text
            named.setText(ingredient_name);

            // set image
            String image = ingredient.getImageURL();
            imaged.setImageUrl(image, imageLoader);
        }
    }

    // todo: add report

    @Override
    public void gotIngredientDetails(Ingredient ingredient) {

        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        ingredient_id = ingredient.getId();
        ingredient_name = ingredient.getName();

        // declare fields of detailed recipe
        TextView ingredientName = findViewById(R.id.ingredient_detail_name);
        TextView ingredientProtein = findViewById(R.id.ingredient_protein);
        TextView ingredientFat = findViewById(R.id.ingredient_fat);
        TextView ingredientCarbs = findViewById(R.id.ingredient_carbs);
        NetworkImageView ingredientImage = findViewById(R.id.ingredient_detail_image);

        // connect selected recipe to views
        ingredientName.setText(ingredient.getName());
        ingredientProtein.setText(ingredient.getProtein() + "% Protein");
        ingredientFat.setText(ingredient.getFat() + "% Fat");
        ingredientCarbs.setText(ingredient.getCarbs() + "% Carbs");

        // set image
        String image = ingredient.getImageURL();
        ingredientImage.setImageUrl(image, imageLoader);
    }

    @Override
    public void gotError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to list of recipes with this ingredient
    public void SearchClicked (View view){
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(IngredientDetailActivity.this, RecipeActivity.class);
        intent.putExtra("ingredient_detail_name", ingredient_name);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }

    public void ReturnClickedI (View view){
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(IngredientDetailActivity.this, MainActivity.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
}