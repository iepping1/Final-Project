package epping.ian.finalproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeDetailRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotRecipeDetails(Recept recipe);
        void gotError(String message);
    }

    private Context context;
    private Callback callback;
    public String recipe_id, message;
    public Recept recipe;

    // constructor for context parameter
    public RecipeDetailRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves recipe information
    void getRecipeDetails(Callback callback, String recipe_id){
        this.callback = callback;
        this.recipe_id = recipe_id;

        // link to the recipe API
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + message + "/information";

        Log.d("what went wrong is", "onResponse: " + recipe);

        // request the data from the API
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null, this, this) {

            // provides API key
            public Map<String, String> getHeaders(){
                Map<String, String> params = new HashMap<>();
                params.put("X-RapidAPI-Key", "0dba1026cfmsh3b124a3d158d5d7p11beddjsn4b004a646531");
                Log.d("Parameters", this.toString());
                return params;
            }
        };
        queue.add(request);
    }

    // listener for succesful requests
    @Override
    public void onResponse(JSONObject object) {

        // define the recipe fields
        String name, vegetarian, gluten, image, instructed, instructions, recipe_id;
        String no_instructions = "NULL";

        try {
            // get all info from the site
            name = object.getString("title");
            vegetarian = "Vegetarian: " + object.getString("vegetarian");
            gluten = "Glutenfree: " + object.getString("glutenFree");
            image = object.getString("image");
            recipe_id = object.getString("id");

            // some recipes dont have instructions
            instructed = object.getString("instructions");
            if ( instructed.toLowerCase().contains(no_instructions.toLowerCase())) {
                instructions = "This recipe has no Instructions"; }
            else{ instructions = instructed;}

            // add new recipe data to detail activity
            recipe = new Recept(name, image, vegetarian, gluten, instructions, recipe_id);
        }
        // exception for network error
        catch (JSONException e) {
            e.printStackTrace();
        }
        // pass recipe back to calling activity
        callback.gotRecipeDetails(recipe);
    }

    // handles request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotError(error.getMessage());
        error.printStackTrace();
    }
}
