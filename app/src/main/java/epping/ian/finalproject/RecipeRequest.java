package epping.ian.finalproject;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeRequest implements Response.ErrorListener, Response.Listener<JSONArray> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotRecipes(ArrayList<Recept> recipes);
        void gotError(String message);
    }

    private Context context;
    private Callback callback;
    String message;
    private ArrayList<Recept> recipes = new ArrayList<>();

    // constructor for context parameter
    public RecipeRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves recipe information
    void getRecipes(Callback callback, String message){
        this.callback = callback;
        this.message = message;

        // link to the recipe API
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ingredients=" + message;

        Log.d("what went wrong is", "onResponse: " + recipes);

                // request the data from the API
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, this, this) {

            // provides API key
            public Map<String, String> getHeaders(){
                Map<String, String> params = new HashMap<>();
                params.put("X-RapidAPI-Key", "0dba1026cfmsh3b124a3d158d5d7p11beddjsn4b004a646531");
                Log.d("Parameters", this.toString());
                return params;
            }
        };
        queue.add(arrayRequest);
    }

    // listener for succesful requests
    @Override
    public void onResponse(JSONArray jsonArray) {

        // define the recipe fields
        String name, image, instructions, vegetarian, gluten, id;

        try {
            // fill list with all recipe data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                    // get all info from the site
                name = object.getString("title");
                image = object.getString("image");
                instructions = "No Instructions";
                vegetarian = "unknown";
                gluten = "unknown";
                id = object.getString("id");
                //instructions = object.getString("instructions");
                //vegetarian = object.getString("vegetarian");
                //gluten = object.getString("glutenFree");

                // add new menu item to arraylist
                //recipes.add(new Recept2(name, image, id));
                recipes.add(new Recept(name, image, instructions, vegetarian, gluten, id));
            }
        }
        // exception for network error
        catch (JSONException e) {
            e.printStackTrace();
        }
        // pass list back to calling activity
        callback.gotRecipes(recipes);
    }

    // handles request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotError(error.getMessage());
        error.printStackTrace();
    }
}
