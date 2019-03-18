# DESIGN DOCUMENT

## Advanced Sketches of the diet app
![](https://github.com/iepping1/Final-Project/blob/master/docs/design1.png?raw=true)

### List of required modules and classes
| Recept|
| ----- |
|- Model1 for any non-random recipe search  > (Information about name, calory amount, id and image)|
|- Model2 for random and detailed recipes > (Information about name, vegetarian/glutenfree status, instructions, id and image)|

| Ingredient|
| ----- |
|- Model1 for initial ingredient search (Information about name, image and a placeholder text)|
|- Model2 for ingredients linked to recipes (Information about name, amount of ingredient used,, id and image) |
|- Model3 for detailed ingredients (Information about name, protein/fat/carb percentages, id and image) |

| AutoIngredientRequest | 
| ------ | 
| - Requests Array data from the autocomplete ingredient search endpoint |
| - Adds simple ingredients (Model1) to the Ingredient list, and to the Detail Activity if the user clicks an item on that list. |

| IngredientRequest | 
| ------ | 
| - Requests Object data from the extended ingredients Array inside the recipe information endpoint |
| - Adds ingredients (Model2) to the Ingredient list a user gets when they look up the ingredients of a recipe |

| RecipeRequest | 
| ------ | 
| - Requests Array data from multiple recipe endpoints, dependent on how the user got to the RecipeActivity (from main or ingredient activity) |
| - Receives message to determine which of two source URLs to use and what the content is (either used number of ingredients or total number of calories). |

| RandomRequest | 
| ------ | 
| - Requests Array data from the random recipe search endpoint, getting 5 results. |
| - Adds recipes (Model2) to the Random recipe list, and to the Recipe Detail Activity if the user clicks an item on that list. |

| IngredientDetailRequest | 
| ------ | 
| - Requests Object data from the food item information endpoint  |
| - Adds all information of the chosen ingredient (Model3) to the detail window |
| (Can only be accessed if the Ingredient was previously requested through IngredientRequest, due to a flaw in the API) |

| RecipeDetailRequest | 
| ------ | 
| - Requests Object data from the recipe information endpoints, if the user clicked on a recipe in the RecipeActivity |
| - Adds all information of the chosen recipe (Model2) to the detail window. |
| (If there are no instructions, a replacement placeholder text is given instead (No Instructions))|

| Other Modules |
| ----- |
| + Imagerequest - Helps each other request get images from the API |
| + An Adapter exists for each list in the app (Ingredient, Random & RecipeAdapter)|

#### API's and Frameworks used in the App.
All data used came from Spoonaculars food site (https://rapidapi.com/spoonacular/api/recipe-food-nutrition)
The Volley framework was used to extract JSONObject and JSONArray data from various RapidAPI endpoints

| List of used Spoonacular's Endpoints |
| ----- |
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients > *Has detailed information of all ingredients*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients/autocomplete > *Finds ingredients that fit a given text input the best*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes > *Has detailed information of all recipes, including its ingredients*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients > *Finds recipes that use a requested ingredient*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByNutrients > *Finds recipes that do not exceed a requested nutrient treshold*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=5 > *Finds 5 random recipes in the database*|








