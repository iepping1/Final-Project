package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity implements ItemRequest.Callback, AutoItemRequest.Callback {


    String recipe, id_message, message;


        // create the ingredient window
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_item);

            Intent intent = getIntent();

            // check from which activity item was chosen
            if (intent.getStringExtra("recipe_id") != null) {
                recipe = intent.getStringExtra("recipe_id");
                message = "recipes/" + recipe + "/information";

                // get ingredient connected to recipe
                ItemRequest request = new ItemRequest(this, message);
                request.getIngredients(this, message);
            }

            else if (intent.getStringExtra("query") != null) {
                message = intent.getStringExtra("query");

                // get ingredient connected to query
                AutoItemRequest autoRequest = new AutoItemRequest(this, message);
                autoRequest.getAutoIngredients(this, message);
            }
        }

        @Override
        public void gotItems(ArrayList<Ingredient> items) {
            ItemAdapter adapter = new ItemAdapter(this, R.layout.ingredient, items);

            // connect adapter to listview
            ListView itemList = findViewById(R.id.itemList);
            itemList.setAdapter(adapter);
            itemList.setOnItemClickListener(new epping.ian.finalproject.ItemActivity.ListClickListener());
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

                Intent intent = new Intent(epping.ian.finalproject.ItemActivity.this, ItemDetailActivity.class);
                Ingredient ingredient = (Ingredient) adapterView.getItemAtPosition(i);

                // check if ingredient has id
                id_message = ingredient.getId();
                if (id_message != null) {
                    intent.putExtra("id_message", id_message.toString());
                }
                else { intent.putExtra("ingredient", ingredient);}

                startActivity(intent);
            }
        }
}
