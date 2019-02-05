package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements RecipeRequest.Callback, RecipeItemRequest.Callback {

    String message, ingredient_input, calory_input, recipe_id;

    // create the recipe window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        //recipe_id = intent.getRecipeId();

        // check from which activity recipe was chosen
        if (intent.getStringExtra("ingredient_detail_name") != null) {
            // catch ingredient item and show 5 connected recipes
            message = intent.getStringExtra("ingredient_detail_name");

            // get the recipe from the site
            RecipeRequest request = new RecipeRequest(this, message);
            request.getRecipes(this, message);
        }
        else if (intent.getStringExtra("calories") != null){
            // catch daily calory treshold and show appropriate recipes
            calory_input = intent.getStringExtra("calories");
            message = "mealplans/generate?targetCalories=" + calory_input + "&timeFrame=day";
            //= "findByNutrients?number=6&maxCalories=" + calory_input + "&minCalories=0";

            // get the recipe from the site
            RecipeItemRequest request = new RecipeItemRequest(this, message);
            request.getRecipes(this, message);
        }
    }

    @Override
    public void gotRecipes(ArrayList<Recept> recipes) {
        ReceptAdapter adapter = new ReceptAdapter(this, R.layout.recept, recipes);

        // connect adapter to listview
        ListView recipeList = findViewById(R.id.recipeList);
        recipeList.setAdapter(adapter);
        recipeList.setOnItemClickListener(new ListClickListener());
    }

    @Override
    public void gotError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to choice when a recipe from list is clicked
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recept", (Recept) adapterView.getItemAtPosition(i));
            //intent.putExtra("recipe_id", recipe_id() );
            startActivity(intent);
        }
    }
}
