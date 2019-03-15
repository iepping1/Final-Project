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

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailRequest.Callback {

    String recipeId, ingredient, message;
    ImageLoader imageLoader;
    ProgressBar spinner;

    // Create recipe detail window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        spinner = findViewById(R.id.progressBarDR);
        spinner.setVisibility(View.GONE);

        // get name of ingredient
        Intent intent = getIntent();

        // request detailed recipe information
        if (intent.getStringExtra("idMessage") != null) {
            message = intent.getStringExtra("idMessage");

            RecipeDetailRequest detailRequest = new RecipeDetailRequest(this, message);
            detailRequest.getRecipeDetails(this, message);
        }
        // request random recipe information
        else {
            // get id of recipe
            Recept recipe = (Recept) intent.getSerializableExtra("recept");
            recipeId = recipe.getRecipeId();

            // declare fields of detailed recipe
            TextView recipeName = findViewById(R.id.recipe_detail_name);
            TextView recipeVegetarian = findViewById(R.id.detail_vegetarian);
            TextView recipeGluten = findViewById(R.id.detail_gluten);
            NetworkImageView recipeImage = findViewById(R.id.recipe_detail_image);
            TextView recipeInstructions = findViewById(R.id.recipe_instructions);

            // prepare special text
            String vegetary = "Vegetarian: " + recipe.getVegetarian();
            String gluten = "Glutenfree: " + recipe.getGluten();

            // set text
            recipeName.setText(recipe.getName());
            recipeVegetarian.setText(vegetary);
            recipeGluten.setText(gluten);
            recipeInstructions.setText(recipe.getInstructions());

            // set image
            String image = recipe.getImage();
            recipeImage.setImageUrl(image, imageLoader);
        }
    }

    @Override
    public void gotRecipeDetails(Recept recipe) {

        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        recipeId = recipe.getRecipeId();

        // declare fields of detailed recipe
        TextView recipeName = findViewById(R.id.recipe_detail_name);
        TextView recipeVegetarian = findViewById(R.id.detail_vegetarian);
        TextView recipeGluten = findViewById(R.id.detail_gluten);
        NetworkImageView recipeImage = findViewById(R.id.recipe_detail_image);
        TextView recipeInstructions = findViewById(R.id.recipe_instructions);

        // connect selected recipe to views
        recipeName.setText(recipe.getName());
        recipeVegetarian.setText(recipe.getVegetarian());
        recipeGluten.setText(recipe.getGluten());
        recipeInstructions.setText(recipe.getInstructions());

        // set image
        String image = recipe.getImage();
        recipeImage.setImageUrl(image, imageLoader);
    }

    @Override
    public void gotRecipeDetailError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to list of recipe's ingredients
    public void IngredientClicked (View view) {
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(RecipeDetailActivity.this, IngredientActivity.class);
        intent.putExtra("recipeId", recipeId);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }

    // return to main activity
    public void ReturnClickedR (View view) {
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
}
