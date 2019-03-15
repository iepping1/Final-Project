# DESIGN DOCUMENT

## Advanced Sketches of the diet app
![](doc/design2.png)

### List of required classes
| Main Activity |
| ------ |
| + Oncreate < *Setup spinner, buttons* | 
| + Onresume < *Clear button prompts* | 
| + RecipeClicked < *Input Calories & Click to find appropriate recipe* | 
| + IngredientClicked < *Input word or letters and Click to find ingredients* | 
| + RandomClicked < *Click to find a randomly selected recipe* | 

| Recept|
| ----- |
|- name, image, content, recipeId < *recipe item for recipe arraylist* |
| - name, image, instruction , vegetarian, gluten, recipeId < *recipe for random list* |
| + getName,  getImage, getInstructions, getRecipeId |
| + getContent, getVegetarian, getGluten |
|+ setName, setImage |

| RecipeActivity | RecipeRequest | RecipeAdapter |
| ------ | ------ | ------- |
| - Oncreate | - context | - context |
| + GotRecipes | + RecipeRequest() | + getView |
| + GotRecipesError | + getRecipes | |
| + ListClickListener | + onResponse | |
|| + onErrorResponse | |

| RecipeDetailActivity | RecipeDetailRequest |
| ------ | ------ | 
| - Oncreate | - context |
| + GotRecipeDetailss | + RecipeDetailRequest() |
| + GotRecipeDetailsError | + getRecipeDetails | 
| + IngredientClicked | + onResponse | 
| + ReturnClickedR | + onErrorResponse | 

| Ingredient |
| ----- |
| - name, image, amount < *ingredient item for autocomplete search* |
| - name, image, amount , ingredientIdd < *item for ingredients in recipe* |
| - name, image, protein, fat, carbs, ingredientId < *item for detailed ingredients* |
| + getName,  getImageUrl, getAmount, getId |
| + getProtein, getFat, getCarbs |
| + setName, setImage |

| IngredientActivity | IngredientRequest | AutoIngredientRequest | IngredientAdapter |
| ------ | ------ | ------- | ------ |
| - Oncreate | - context | - context | - context |
| + GotIngredients | + IngredientRequest() | + AutoIngredientRequest() | + getView |
| + GotIngredientsError | + getIngredients | + getAutoIngredients  |
| + ListClickListener | + onResponse | + onResponse |
|| + onErrorResponse | + onResponse |

| IngredientDetailActivity | IngredientDetailRequest |
| ------ | ------ | 
| - Oncreate | - context | 
| + GotIngredientDetail | + IngredientDetailRequest() |
| + GotIngredientDetailError | + getIngredientDetails | 
| + SearchClicked | + onResponse | 
| + ReturnClickedI | + onErrorResponse | 

| RandomActivity | RandomRequest | RandomAdapter |
| ------ | ------ | ------- |
| - Oncreate | - context | -context |
| + GotRandomRecipes | + RandomRequest() | + getView |
| + GotRandomError | + getRandomRecipes | |
| + ListClickListener | + onResponse | |
|| + onErrorResponse | |

| ImageRequest |
| ----- |
| - context |
| + ImageRequest() |
| + getInstance() |
| + RequestQueue |
| + getImageLoader |

#### List of used APIs
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients/autocomplete
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByNutrients
- https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=5

*Data, in the form of JSONobjects or JSONArrays, was extracted from the rapidapi site using Volley's request methods.*






