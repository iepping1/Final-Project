package epping.ian.finalproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
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
import java.util.Map;
import java.util.HashMap;

public class IngredientRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotIngredients(ArrayList<Ingredient> ingredients);
        void gotIngredientsError(String message);
    }

    private Context context;
    private Callback callback;
    private String message;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    // constructor for context parameter
    public IngredientRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves selected ingredients
    public void getIngredients(Callback callback, String message) {
        this.callback = callback;
        this.message = message;

        // link to the API site
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/" + message;

        // define the request
        RequestQueue queue = Volley.newRequestQueue(context);

        // request the data from the API
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

        JSONArray IngredientArray;

        try {
            IngredientArray = response.getJSONArray("extendedIngredients");

            // fill list with all ingredient items
            for (int i = 0; i < IngredientArray.length(); i++) {
                JSONObject object = IngredientArray.getJSONObject(i);

                // get all info from the site
                String ingredientId = object.getString("id");
                String name = object.getString("name");
                String imaged = object.getString("image");
                String image = "https://spoonacular.com/cdn/ingredients_100x100/" + imaged;
                String amount = object.getString("originalString");

                // add new ingredient item to arraylist
                ingredients.add(new Ingredient(name, image, amount, ingredientId));
            }
        }
        // exception for parsing error
        catch (JSONException e) {
            callback.gotIngredientsError(e.getMessage());
            e.printStackTrace();
        }
        // pass list back to calling activity
        callback.gotIngredients(ingredients);
    }
    // handles network request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotIngredientsError(error.getMessage());
        error.printStackTrace();
    }
}