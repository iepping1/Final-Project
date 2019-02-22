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

public class IngredientActivity extends AppCompatActivity implements IngredientRequest.Callback, AutoIngredientRequest.Callback {


    String recipe, id_message, message;
    ProgressBar spinner;


        // create the ingredient window
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item);

            spinner = findViewById(R.id.progressBarI);
            spinner.setVisibility(View.GONE);

            Intent intent = getIntent();

            // check from which activity item was chosen
            if (intent.getStringExtra("recipe_id") != null) {
                recipe = intent.getStringExtra("recipe_id");
                message = "recipes/" + recipe + "/information";

                // get ingredient connected to recipe
                IngredientRequest request = new IngredientRequest(this, message);
                request.getIngredients(this, message);
            }

            else if (intent.getStringExtra("query") != null) {
                message = intent.getStringExtra("query");

                // get ingredient connected to query
                AutoIngredientRequest autoRequest = new AutoIngredientRequest(this, message);
                autoRequest.getAutoIngredients(this, message);
            }
        }

        @Override
        public void gotItems(ArrayList<Ingredient> items) {
            IngredientAdapter adapter = new IngredientAdapter(this, R.layout.ingredient, items);

            // connect adapter to listview
            ListView itemList = findViewById(R.id.itemList);

            // connect header to list
            LayoutInflater inflater = getLayoutInflater();
            ViewGroup header =  (ViewGroup)inflater.inflate(R.layout.listview_header, itemList, false);
            TextView HeaderText = header.findViewById(R.id.header);
            HeaderText.setText("List of Ingredients");
            itemList.addHeaderView(header, null, false);

            itemList.setAdapter(adapter);
            itemList.setOnItemClickListener(new IngredientActivity.ListClickListener());
        }

        @Override
        public void gotItemsError(String message) {
            // send a message if error has occured
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }

        // switch to choice when a recipe from list is clicked
        public class ListClickListener implements AdapterView.OnItemClickListener {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //spinner.setVisibility(View.VISIBLE);
                Intent intent = new Intent(IngredientActivity.this, IngredientDetailActivity.class);
                Ingredient ingredient = (Ingredient) adapterView.getItemAtPosition(i);

                // check if ingredient has id
                id_message = ingredient.getId();
                if (id_message != null) {
                    intent.putExtra("id_message", id_message.toString());
                }
                else { intent.putExtra("ingredient", ingredient);}

                startActivity(intent);
                //spinner.setVisibility(View.GONE);
            }
        }
}
