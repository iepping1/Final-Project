package epping.ian.finalproject;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeItemRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotRecipes(ArrayList<Recept> recipes);
        void gotError(String message);
    }

    private Context context;
    private Callback callback;
    private String message;
    private ArrayList<Recept> recipes = new ArrayList<>();

    // constructor for context parameter
    public RecipeItemRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves recipe information
    void getRecipes(Callback callback, String message){
        this.callback = callback;
        this.message = message;

        // link to the recipe API
        //String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?number=5&ingredients=" + message;
        //String baseUrl = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/mealplans/generate?targetCalories=";
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + message;

        Log.d("what went wrong is", "onResponse: " + recipes);

        // request the data from the API
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, this, this) {

            // provides API key
            public Map<String, String> getHeaders(){
                Map<String, String> params = new HashMap<>();
                params.put("X-RapidAPI-Key", "0dba1026cfmsh3b124a3d158d5d7p11beddjsn4b004a646531");
                Log.d("Parameters", this.toString());
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    // listener for succesful requests
    @Override
    public void onResponse(JSONObject response) {

        // define the recipe fields
        String name, image, image_link, id, instructions, vegetarian, gluten;

        //define list of recipes
        JSONArray jsonArray;

        try {
            // extract and define array
            jsonArray = response.getJSONArray("meals");

            // fill list with all recipe data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                //String itemCategory = item.getString("category");

                //if(this.category.equals(itemCategory)) {
                // get all info from the site
                name = object.getString("title");
                image_link = object.getString("image");
                image = "https://spoonacular.com/recipeImages/" + image_link;
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
