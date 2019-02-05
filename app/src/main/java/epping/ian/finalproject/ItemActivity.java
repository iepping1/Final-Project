package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity implements ItemRequest.Callback{


    String recipe;
    String query;
    String message;


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
            }

            //else { message = Integer.toString(random); }
            else if (intent.getStringExtra("query") != null) {
                    query = intent.getStringExtra("query");
                    message = "food/products/search?number=5&query=" + query;
            }

            // get the ingredient from the site
            ItemRequest request = new ItemRequest(this, message);
            request.getIngredients(this, message);
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
                intent.putExtra("ingredient", (Ingredient) adapterView.getItemAtPosition(i));
                startActivity(intent);
            }
        }
}
