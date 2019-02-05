package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Set up calory and ingredient queries
    String calories;
    EditText getCalory;
    String query;
    EditText getQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCalory = findViewById(R.id.getCalory);
        getQuery = findViewById(R.id.getQuery);
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
            // pass on calory treshold
            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
            intent.putExtra("calories", calories);
            startActivity(intent);
        }
    }

    // Move to ingredient list
    public void IngredientClicked (View view){
        query = getQuery.getText().toString();

        if(query.equals("")){
            // ask user to input calory treshold
            Toast toast = Toast.makeText(this, "Need information first", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Intent intent = new Intent(MainActivity.this, ItemActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    // Move to random recipe
    public void RandomClicked (View view){
        Intent intent = new Intent(MainActivity.this, RandomActivity.class);
        startActivity(intent);
    }
}