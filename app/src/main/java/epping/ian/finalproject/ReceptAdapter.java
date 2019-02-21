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

// connect menu item with activity menu
public class ReceptAdapter extends ArrayAdapter<Recept> {

    ArrayList<Recept> recipes;
    NetworkImageView imaged;
    ImageLoader imageLoader;
    private Context context;

    public ReceptAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Recept> objects) {
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
        Recept recipe = recipes.get(position);

        String name = recipe.getName();
        String image = recipe.getImage();
        String content = recipe.getContent();

        //get references to item fields
        TextView named = convertView.findViewById(R.id.recipe_name);
        TextView counted = convertView.findViewById(R.id.recipe_content);
        imaged = convertView.findViewById(R.id.recept_image);

        // set text info to views
        named.setText(name);
        counted.setText(content);

        // set image to views
        imageLoader = ImageRequest.getInstance(this.getContext())
                .getImageLoader();
        imaged.setImageUrl(image, imageLoader);

        return convertView;
    }
}