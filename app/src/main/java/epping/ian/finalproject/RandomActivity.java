package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RandomActivity extends AppCompatActivity implements RandomRequest.Callback{

    private ProgressBar spinner;

    // create the recipe window
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String recept = intent.getStringExtra("recept");
        setContentView(R.layout.activity_random);

        spinner = findViewById(R.id.progressBar2);
        spinner.setVisibility(View.GONE);

        // get random recipe from the site
        RandomRequest request = new RandomRequest(this);
        request.getRandomRecipes(this, recept);
    }

    @Override
    public void gotRecipes(ArrayList<Recept> recipes) {
        RandomAdapter adapter = new RandomAdapter(this, R.layout.recept, recipes);

        // connect adapter to listview
        ListView randomList = findViewById(R.id.randomList);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header =  (ViewGroup)inflater.inflate(R.layout.listview_header, randomList, false);
        TextView HeaderText = header.findViewById(R.id.header);
        HeaderText.setText("List of Random Recipes");
        randomList.addHeaderView(header, null, false);

        randomList.setAdapter(adapter);
        randomList.setOnItemClickListener(new ListClickListener());
    }

    @Override
    public void gotRecipesError(String message) {
        // send a message if error has occured
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // switch to choice when a recipe from list is clicked
    public class ListClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            //spinner.setVisibility(View.VISIBLE);
            Intent intent = new Intent(RandomActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recept", (Recept) adapterView.getItemAtPosition(i));
            startActivity(intent);
            spinner.setVisibility(View.GONE);
        }
    }
}