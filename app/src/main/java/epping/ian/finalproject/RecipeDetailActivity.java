package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailRequest.Callback {

    String recipe_id, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ImageLoader imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        // get name of ingredient
        Intent intent = getIntent();
        message = intent.getStringExtra("ingredient_detail_name");

        // get id of recipe
        Recept recipe = (Recept) intent.getSerializableExtra("recept");
        recipe_id = recipe.getRecipeId();

        // get the recipe from the site
        RecipeDetailRequest detailRequest = new RecipeDetailRequest(this, message);
        detailRequest.getRecipeDetails(this, message);

        // give it the proper data
        TextView named = findViewById(R.id.recipe_name);
        TextView instructed = findViewById(R.id.recipe_instructions);
        TextView vegetated = findViewById(R.id.recipe_vegetarian);
        TextView gluted = findViewById(R.id.recipe_gluten);
        NetworkImageView imaged = findViewById(R.id.recept_detail_image);

        // set text
        named.setText(recipe.getName());
        instructed.setText(recipe.getInstructions());
        //vegetated.setText(recipe.getVegetarian());
        //gluted.setText(recipe.getGluten());

        // set image
        String image = recipe.getImage();
        imaged.setImageUrl(image, imageLoader);
    }

    @Override
    public void gotRecipeDetails(ArrayList<Recept> recipes) {

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
}
