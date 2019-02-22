package epping.ian.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

// connect random recipe item with random activity list
public class RandomAdapter extends ArrayAdapter<Recept> {

    ArrayList<Recept> recipes;
    private NetworkImageView imaged;
    private ImageLoader imageLoader;
    private Context context;

    public RandomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recept> objects) {
        super(context, resource, objects);
        recipes = objects;
    }

    // create fields for adapterview
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inf = LayoutInflater.from(getContext());
            convertView = inf.inflate(R.layout.recept, parent, false);
        }

        // get item info
        Recept recipe = recipes.get(position);

        String name = recipe.getName();
        String image = recipe.getImage();
        String vegetarian = "Vegetarian: " + recipe.getVegetarian();
        String gluten = "Glutenfree: " + recipe.getGluten();

        //get references to item fields
        TextView named = convertView.findViewById(R.id.recipe_name);
        TextView vegetated = convertView.findViewById(R.id.recipe_content);
        TextView gluted = convertView.findViewById(R.id.recipe_gluten);
        imaged = convertView.findViewById(R.id.recipe_image);

        // set text info to views
        named.setText(name);
        vegetated.setText(vegetarian);
        gluted.setText(gluten);

        // set image to views
        imageLoader = ImageRequest.getInstance(this.getContext())
                .getImageLoader();
        imaged.setImageUrl(image, imageLoader);

        return convertView;
    }
}
