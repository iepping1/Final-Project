package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Set up calory and ingredient queries
    String calories;
    EditText getCalory;
    String query;
    EditText getQuery;
    private ProgressBar spinner;

    // Start up main screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up progressbar spinner
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        getCalory = findViewById(R.id.getCalory);
        getQuery = findViewById(R.id.getQuery);
    }

    // clear text inputs when returning to main
    protected void onResume(){
        super.onResume();
        getCalory.setText("");
        getQuery.setText("");
    }

    // Move to selected recipe
    public void RecipeClicked (View view){

        calories = getCalory.getText().toString();

        if(calories.equals("")){
            // ask user to input calory treshold
            Toast toast = Toast.makeText(this, "Please enter your Calory Treshold", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            // show loading spinner
            spinner.setVisibility(View.VISIBLE);

            // pass on calory treshold and move to recipe list
            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
            intent.putExtra("calories", calories);
            startActivity(intent);

            // clear spinner
            spinner.setVisibility(View.GONE);
        }
    }

    // Search for ingredients
    public void IngredientClicked (View view){
        query = getQuery.getText().toString();

        if(query.equals("")){
            // ask user to input calory treshold
            Toast toast = Toast.makeText(this, "Need some letters first", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            spinner.setVisibility(View.VISIBLE);

            // move to ingredient list
            Intent intent = new Intent(MainActivity.this, IngredientActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
            spinner.setVisibility(View.GONE);
        }
    }

    // Search recipes randomly
    public void RandomClicked (View view){
        // show loading spinner
        spinner.setVisibility(View.VISIBLE);

        // move to random recipe list
        Intent intent = new Intent(MainActivity.this, RandomActivity.class);
        startActivity(intent);
        spinner.setVisibility(View.GONE);
    }
}