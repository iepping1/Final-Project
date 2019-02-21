package epping.ian.finalproject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ItemDetailRequest implements Response.ErrorListener, Response.Listener<JSONObject> {

    // call methods for error and succesful requests
    public interface Callback {
        void gotIngredientDetails(Ingredient ingredient);
        void gotError(String message);
    }

    private Context context;
    private Callback callback;
    public String ingredient_id, message;
    public Ingredient ingredient;

    // constructor for context parameter
    public ItemDetailRequest(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    // retrieves recipe information
    void getIngredientDetails(Callback callback, String ingredient_id){
        this.callback = callback;
        this.ingredient_id = ingredient_id;

        // link to the recipe API
        String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients/"
                + message + "/information?amount=100&unit=gram";

        Log.d("what went wrong is", "onResponse: " + ingredient);

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
        String name, image, imaged, ingredient_id, protein, fat, carbs;
        JSONObject nutrients, breakdown;

        try {
            // get all info from the site
            name = object.getString("name");
            imaged = object.getString("image");
            image = "https://spoonacular.com/cdn/ingredients_500x500/" + imaged;
            ingredient_id = object.getString("id");

            nutrients = object.getJSONObject("nutrition");
            breakdown = nutrients.getJSONObject("caloricBreakdown");

            protein = breakdown.getString("percentProtein");
            fat = breakdown.getString("percentFat");
            carbs = breakdown.getString("percentCarbs");

            // add new recipe data to detail activity
            ingredient = new Ingredient(name, image, protein, fat, carbs, ingredient_id);
        }
        // exception for network error
        catch (JSONException e) {
            e.printStackTrace();
        }
        // pass recipe back to calling activity
        callback.gotIngredientDetails(ingredient);
    }

    // handles request errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotError(error.getMessage());
        error.printStackTrace();
    }
}
