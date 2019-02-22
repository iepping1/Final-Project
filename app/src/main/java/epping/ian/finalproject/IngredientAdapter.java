package epping.ian.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

// connect ingredient item with ingredient list
public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    ArrayList<Ingredient> ingredients;
    NetworkImageView imaged;
    ImageLoader imageLoader;
    private Context context;

    public IngredientAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Ingredient> objects) {
        super(context, resource, objects);
        ingredients = objects;
    }

    // create fields for adapterview
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inf = LayoutInflater.from(getContext());
            convertView = inf.inflate(R.layout.ingredient, parent, false);
        }

        // get item info
        Ingredient ingredient = ingredients.get(position);

        String name = ingredient.getName();
        String image = ingredient.getImageURL();
        String amount = ingredient.getAmount();

        //get references to item fields
        TextView named = convertView.findViewById(R.id.ingredient_name);
        TextView amounted = convertView.findViewById(R.id.ingredient_amount);
        imaged = convertView.findViewById(R.id.ingredient_image);

        // set text info to views
        named.setText(name);
        amounted.setText(amount);

        // set image to views
        imageLoader = ImageRequest.getInstance(this.getContext())
                .getImageLoader();
        imaged.setImageUrl(image, imageLoader);

        return convertView;
    }
}
