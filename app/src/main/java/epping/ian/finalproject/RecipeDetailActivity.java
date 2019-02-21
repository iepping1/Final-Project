package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailRequest.Callback {

    String recipe_id, ingredient, message;
    ImageLoader imageLoader;

    // Create recipe detail window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        // get name of ingredient
        Intent intent = getIntent();
        //ingredient = intent.getStringExtra("ingredient_detail_name");

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
            TextView recipeName = findViewById(R.id.recipe_name);
            TextView recipeInstructions = findViewById(R.id.recipe_instructions);
            NetworkImageView recipeImage = findViewById(R.id.recept_detail_image);

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
        TextView recipeName = findViewById(R.id.recipe_name);
        TextView recipeInstructions = findViewById(R.id.recipe_instructions);
        NetworkImageView recipeImage = findViewById(R.id.recept_detail_image);

        // set scroller of textview
        //recipeInstructions.setMovementMethod(new ScrollingMovementMethod());

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
        Intent intent = new Intent(RecipeDetailActivity.this, ItemActivity.class);
        intent.putExtra("recipe_id", recipe_id);
        startActivity(intent);
    }

    public void ReturnClickedR (View view){
        Intent intent = new Intent(RecipeDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
