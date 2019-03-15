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

public class RandomRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotRandomRecipes(ArrayList<Recept> recipes);
        void gotRandomError(String message);
    }

    private Context context;
    private Callback callback;
    private ArrayList<Recept> recipes = new ArrayList<>();

    // constructor for context parameter
    public RandomRequest(Context context) {
        this.context = context;
    }

    // retrieves recipe information
    void getRandomRecipes(Callback callback, String recept) {
        this.callback = callback;

        // link to the recipe API
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=5";

        // request the data from the API
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, this, this) {

            // provides API key
            public Map<String, String> getHeaders() {
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

        //define list of recipes
        JSONArray jsonArray;

        try {
            // extract and define array
            jsonArray = response.getJSONArray("recipes");

            // fill list with all recipe data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                // get all info from the random recipe
                String name = item.getString("title");
                String image = item.getString("image");
                String instructions = item.getString("instructions");
                String vegetarian = item.getString("vegetarian");
                String gluten = item.getString("glutenFree");
                String recipeId = item.getString("id");

                // add new recipe item to arraylist
                recipes.add(new Recept(name, image, vegetarian, gluten, instructions, recipeId));
            }
        }
        // exception for parsing error
        catch (JSONException e) {
            callback.gotRandomError(e.getMessage());
            e.printStackTrace();
        }
        // pass list back to calling activity
        callback.gotRandomRecipes(recipes);
    }

    // handles network request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotRandomError(error.getMessage());
        error.printStackTrace();
    }
}
