package epping.ian.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ItemDetailActivity extends AppCompatActivity {

    String ingredient_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ImageLoader imageLoader = ImageRequest.getInstance(this.getApplicationContext()).getImageLoader();

        // show proper ingredient item
        Intent intent = getIntent();
        Ingredient ingredient = (Ingredient) intent.getSerializableExtra("ingredient");

        ingredient_name = ingredient.getName();

        // give it the proper data
        TextView named = findViewById(R.id.ingredient_detail_name);
        NetworkImageView imaged = findViewById(R.id.ingredient_detail_image);

        // set name text
        named.setText(ingredient.getName());

        // set image
        String image = ingredient.getImageURL();
        imaged.setImageUrl(image, imageLoader);
    }

    // switch to list of recipes with this ingredient
    public void SearchClicked (View view){
        Intent intent = new Intent(ItemDetailActivity.this, RecipeActivity.class);
        intent.putExtra("ingredient_detail_name", ingredient_name);
        startActivity(intent);
    }
}