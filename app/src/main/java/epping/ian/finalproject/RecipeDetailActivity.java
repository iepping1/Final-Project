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

    String recipe_id, ingredient, message;
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
        if (intent.getStringExtra("id_message") != null) {
            message = intent.getStringExtra("id_message");

            RecipeDetailRequest detailRequest = new RecipeDetailRequest(this, message);
            detailRequest.getRecipeDetails(this, message);
        }
        // request random recipe information
        else {
            // get id of recipe
            Recept recipe = (Recept) intent.getSerializableExtra("recept");
            recipe_id = recipe.getRecipeId();

            // declare fields of detailed recipe
            TextView recipeName = findViewById(R.id.recipe_detail_name);
            TextView recipeInstructions = findViewById(R.id.recipe_instructions);
            NetworkImageView recipeImage = findViewById(R.id.recipe_detail_image);

            // set text
            recipeName.setText(recipe.getName());
            recipeInstructions.setText(recipe.getInstructions());

            // set image
            String image = recipe.getImage();
            recipeImage.setImageUrl(image, imageLoader);
        }
    }

    @Override
    public void gotRecipeDetails(Recept recipe) {

        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        recipe_id = recipe.getRecipeId();

        // declare fields of detailed recipe
        TextView recipeName = findViewById(R.id.recipe_detail_name);
        TextView recipeInstructions = findViewById(R.id.recipe_instructions);
        NetworkImageView recipeImage = findViewById(R.id.recipe_detail_image);

        // connect selected recipe to views
        recipeName.setText(recipe.getName());
        recipeInstructions.setText(recipe.getInstructions());

        // set image
        String image = recipe.getImage();
        recipeImage.setImageUrl(image, imageLoader);
    }

    @Override
    public void gotError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to list of recipe's ingredients
    public void IngredientClicked (View view){
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(RecipeDetailActivity.this, IngredientActivity.class);
        intent.putExtra("recipe_id", recipe_id);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }

    public void ReturnClickedR (View view){
        spinner.setVisibility(View.VISIBLE);
        Intent intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
}
