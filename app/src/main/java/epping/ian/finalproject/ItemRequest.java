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
import java.util.Map;
import java.util.HashMap;

public class ItemRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotItems(ArrayList<Ingredient> ingredients);
        void gotItemsError(String message);
    }

    private Context context;
    private Callback callback;
    private String message;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    // constructor for context parameter
    public ItemRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves recipe ID
    public void getIngredients(Callback callback, String message){
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
            public Map<String, String> getHeaders() throws AuthFailureError {
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

        // define ingredient fields and array
        String id, name, image, imaged, amount;
        JSONArray IngredientArray;

        try {
            if (message.charAt(0) == 'r') {
                IngredientArray = response.getJSONArray("extendedIngredients");

                // fill list with all ingredient items
                for (int i = 0; i < IngredientArray.length(); i++) {
                    JSONObject object = IngredientArray.getJSONObject(i);

                    // get all info from the site
                    id = object.getString("id");
                    name = object.getString("name");
                    imaged = object.getString("image");
                    image = "https://spoonacular.com/cdn/ingredients_100x100/" + imaged;
                    amount = object.getString("originalString");

                    // add new ingredient item to arraylist
                    ingredients.add(new Ingredient(id, name, image, amount));
                }
            }
            else {
                IngredientArray = response.getJSONArray("products");

                // fill list with all ingredient items
                for (int i = 0; i < IngredientArray.length(); i++) {
                    JSONObject object = IngredientArray.getJSONObject(i);

                    // get all info from the site
                    id = object.getString("id");
                    name = object.getString("title");
                    image = object.getString("image");
                    amount = "null";

                    // add new ingredient item to arraylist
                    ingredients.add(new Ingredient(id, name, image, amount));
                }
            }
        }
        // exception for network error
        catch (JSONException e) {
            e.printStackTrace();
        }
        // pass list back to calling activity
        callback.gotItems(ingredients);
    }
    // handles request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotItemsError(error.getMessage());
        error.printStackTrace();
    }
}