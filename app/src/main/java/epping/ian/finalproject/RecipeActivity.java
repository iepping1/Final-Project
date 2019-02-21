package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity implements RecipeRequest.Callback{

    String message, id_message, ingredient_input, calory_input;

    // Create recipe list window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();

        // check from which activity recipe was chosen
        if (intent.getStringExtra("ingredient_detail_name") != null) {

            // catch ingredient item and show 5 connected recipes
            ingredient_input = intent.getStringExtra("ingredient_detail_name");
            message = "Ingredients?number=5&ingredients=" + ingredient_input;
        }
        else if (intent.getStringExtra("calories") != null){

            // catch daily calory treshold and show appropriate recipes
            calory_input = intent.getStringExtra("calories");
            message = "Nutrients?number=6&maxCalories=" + calory_input + "&minCalories=0";
        }

        // get the recipe from the site
        RecipeRequest request = new RecipeRequest(this, message);
        request.getRecipes(this, message);
    }

    @Override
    public void gotRecipes(ArrayList<Recept> recipes) {
        ReceptAdapter adapter = new ReceptAdapter(this, R.layout.recept, recipes);

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
    public void gotError(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to choice when a recipe from list is clicked
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Intent intent = new Intent(RecipeActivity.this, RecipeDetailActivity.class);
            Recept recipe = (Recept) adapterView.getItemAtPosition(i);

            // pass on id of recipe
            id_message = recipe.getRecipeId();
            intent.putExtra("id_message", id_message.toString() );
            startActivity(intent);
        }
    }
}