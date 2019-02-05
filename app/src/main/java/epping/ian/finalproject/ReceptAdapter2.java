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

// connect menu item with activity menu
public class ReceptAdapter2 extends ArrayAdapter<Recept2> {

    ArrayList<Recept2> recipes;
    NetworkImageView imaged;
    ImageLoader imageLoader;
    private Context context;

    public ReceptAdapter2(@NonNull Context context, int resource, @NonNull ArrayList<Recept2> objects) {
        super(context, resource, objects);
        recipes = objects;
    }

    // create fields for adapterview
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inf = LayoutInflater.from(getContext());
            convertView = inf.inflate(R.layout.recept, parent, false);
        }

        // get item info
        Recept2 recipe = recipes.get(position);

        String name = recipe.getName();
        //String instructions = recipe.getInstructions();
        //String vegetarian = recipe.getVegetarian().toString();
        //String gluten = recipe.getGluten().toString();
        String image = recipe.getImage();
        String id = recipe.getRecipeId();

        //get references to item fields
        TextView named = convertView.findViewById(R.id.recipe_name);
        //TextView vegetated = convertView.findViewById(R.id.recipe_vegetarian);
        //TextView gluted = convertView.findViewById(R.id.recipe_gluten);
        imaged = convertView.findViewById(R.id.recept_image);

        // set text info to views
        named.setText(name);
        //vegetated.setText(vegetarian);
        //gluted.setText(gluten);

        // set image to views
        imageLoader = ImageRequest.getInstance(this.getContext())
                .getImageLoader();
        imaged.setImageUrl(image, imageLoader);

        return convertView;
    }
}
