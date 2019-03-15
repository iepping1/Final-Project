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

    String ingredientName, message, ingredientId;
    ImageLoader imageLoader;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);
        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();
        spinner = findViewById(R.id.progressBarDI);
        spinner.setVisibility(View.GONE);

        // show proper ingredient item
        Intent intent = getIntent();

        // request detailed ingredient information
        if (intent.getStringExtra("idMessage") != null) {
            message = intent.getStringExtra("idMessage");

            IngredientDetailRequest detailRequest = new IngredientDetailRequest(this, message);
            detailRequest.getIngredientDetails(this, message);
        }
        else {
            // get name of ingredient
            Ingredient ingredient = (Ingredient) intent.getSerializableExtra("ingredient");
            ingredientName = ingredient.getName();

            // give it the proper data
            TextView named = findViewById(R.id.ingredient_detail_name);
            NetworkImageView imaged = findViewById(R.id.ingredient_detail_image);

            // set name text
            named.setText(ingredientName);

            // set image
            String image = ingredient.getImageURL();
            imaged.setImageUrl(image, imageLoader);
        }
    }

    @Override
    public void gotIngredientDetails(Ingredient ingredient) {

        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        ingredientId = ingredient.getId();
        ingredientName = ingredient.getName();

        // declare fields of detailed recipe
        TextView ingredientDetailName = findViewById(R.id.ingredient_detail_name);
        TextView ingredientProtein = findViewById(R.id.ingredient_protein);
        TextView ingredientFat = findViewById(R.id.ingredient_fat);
        TextView ingredientCarbs = findViewById(R.id.ingredient_carbs);
        NetworkImageView ingredientImage = findViewById(R.id.ingredient_detail_image);

        String protein = ingredient.getProtein() + "% Protein";
        String fatty = ingredient.getFat() + "% Fat";
        String carbo = ingredient.getCarbs() + "% Carbs";

        // connect selected recipe to views
        ingredientDetailName.setText(ingredient.getName());
        ingredientProtein.setText(protein);
        ingredientFat.setText(fatty);
        ingredientCarbs.setText(carbo);

        // set image
        String image = ingredient.getImageURL();
        ingredientImage.setImageUrl(image, imageLoader);
    }

    @Override
    public void gotIngredientDetailError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to list of recipes with this ingredient
    public void SearchClicked (View view) {
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(IngredientDetailActivity.this, RecipeActivity.class);
        intent.putExtra("ingredientDetailName", ingredientName);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }

    public void ReturnClickedI (View view) {
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(IngredientDetailActivity.this, MainActivity.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
}