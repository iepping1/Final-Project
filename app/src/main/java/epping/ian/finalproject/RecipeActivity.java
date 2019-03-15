package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements RecipeRequest.Callback{

    String message, idMessage, ingredientInput, caloryInput;
    ProgressBar spinner;

    // Create recipe list window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        spinner = findViewById(R.id.progressBarR);
        spinner.setVisibility(View.GONE);

        Intent intent = getIntent();

        // check from which activity recipe was chosen
        if (intent.getStringExtra("ingredientDetailName") != null) {

            // catch ingredient item and show 5 connected recipes
            ingredientInput = intent.getStringExtra("ingredientDetailName");
            message = "Ingredients?number=5&ingredients=" + ingredientInput;
        }
        else if (intent.getStringExtra("calories") != null) {

            // catch daily calory treshold and show appropriate recipes
            caloryInput = intent.getStringExtra("calories");
            message = "Nutrients?number=6&maxCalories=" + caloryInput + "&minCalories=0";
        }

        // get the recipe from the site
        RecipeRequest request = new RecipeRequest(this, message);
        request.getRecipes(this, message);
    }

    @Override
    public void gotRecipes(ArrayList<Recept> recipes) {
        RecipeAdapter adapter = new RecipeAdapter(this, R.layout.recept, recipes);

        // find list
        ListView recipeList = findViewById(R.id.recipeList);

        // adds header to list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header =  (ViewGroup)inflater.inflate(R.layout.listview_header, recipeList, false);
        TextView HeaderText = header.findViewById(R.id.header);
        HeaderText.setText("List of Recipes");
        recipeList.addHeaderView(header, null, false);

        // connect adapter to list
        recipeList.setAdapter(adapter);
        recipeList.setOnItemClickListener(new ListClickListener());
    }

    // send a message if error has occured
    @Override
    public void gotRecipesError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to choice when a recipe from list is clicked
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            spinner.setVisibility(View.VISIBLE);

            Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
            Recept recipe = (Recept) adapterView.getItemAtPosition(i);

            // pass on id of recipe
            idMessage = recipe.getRecipeId();
            intent.putExtra("idMessage", idMessage.toString() );
            startActivity(intent);
            spinner.setVisibility(View.GONE);
        }
    }
}