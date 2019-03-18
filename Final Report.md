#RAPPORTAGE

In deze app kan je recepten en ingredienten opzoeken kwa caloriën, ingredienten, of willekeurige recepten proberen.

![](https://github.com/iepping1/Final-Project/blob/master/docs/MainActivitySmall.png?raw=true)

## Final Overview of the diet app
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
| - Requests Object data from the food item information Data endpoint  |
| - Adds all information of the chosen ingredient (Model3) to the detail window |
| (Can only be accessed if the Ingredient was previously requested through IngredientRequest, due to a flaw in the API) |

| RecipeDetailRequest | 
| ------ | 
| - Requests Object data from the recipe information Data endpoints, if the user clicked on a recipe in the RecipeActivity |
| - Adds all information of the chosen recipe (Model2) to the detail window. |
| (If there are no instructions, a replacement placeholder text is given instead (No Instructions))|

| Other Modules |
| ----- |
| + Imagerequest - Helps each other request get images from the API |
| + An Adapter exists for each list in the app (Ingredient, Random & RecipeAdapter)|

#### API's and Frameworks used in the App.
All data used came from Spoonaculars food site (https://rapidapi.com/spoonacular/api/recipe-food-nutrition)
The Volley framework was used to extract JSONObject and JSONArray data from various RapidAPI endpoints. 
Spoonacular divides its information across several actions. (GET, POST, SEARCH, DATA). Only the latter two were used for this app.

| List of used Spoonacular's Endpoints |
| ----- |
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients > *Has detailed Data information of all ingredients*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/food/ingredients/autocomplete > *Searches ingredients that fit a given text input the best*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes > *Has detailed Data information of all recipes, including its ingredients*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients > *Searches recipes that use a requested ingredient*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByNutrients > *Searches recipes that do not exceed a requested nutrient treshold*|
| https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=5 > *Searches 5 random recipes in the database*|

#### CHALLENGES.
Some obstacles were easy to overcome. Learning how to use the API through KEYS and subscribing to RapidAPI (for a montly fee of zero!) allowed me to access one of the most comprehensive food databases on the net.
Getting the right data was trickier, as each endpoint had its own method of storing its data. They stored their information in arraus, objects, or both. Some arrays and objects did not have names and had to be extracted more directly. Some arrays store their images with a full URL, while others did not. A lot of checks were required to streamline the visual output.
Getting the right variable to show up in the proper view proved to be a problem for a little while as well, in addition to the usual experimenting with style, state saves, dimensions and landscape views.
What required the most work was creating the link between one APi between the other. Thankfully the IDnumbers of all recipes and ingredients stayed the same throughout the database. By storing that IDnumber and passing it along as a message to the next activity, I could ensure that the user would find ingredients through recipes and vice versa. It also allowed me to (in most instances) pull up the Data endpoints of every item, regardless of which Search was used to get there.
Finally, an incredible amount of code was necessary for this project. The overall scope could be decreased a little by linking multiple searches to the same activity and request class and by declaring various ingredient/recipe models in one java class. 

#### DECISIONS
Overall the app works close to what was intended, though it could have been more complex. Several features could not be added within the timeframe. (Though I gladly would have done it, if not for that). One such addition was the ability to add your favorite recipe to your own database; storing all the details of that recipe on your phone so you could pull it back up from a list just by going back to the main window. No such feature exists in the project now.
Another thing I would have liked to add was a more involved method of managing your diet. Right now you can guess how much calories you do not want to exceed and put that in as a limiter of sorts. But with an extra function, one might have been able to calculate by thenmselves how many calories they are allowed to have. 
Dividing the calories between protein, carbs and fats would also have been a nice inclusion. Even if that demands a lot of information from the user as well. In general, it was possible to add more search options like finding recipes by protein percentage or whether or not they were vegetarian. But I didnt include those for the sake of keeping the app simple and not too confusing. That data can still eventually be found in the detail windows.
One regret was the choice of API. The quantity of data is a nice thing to have. But the work might have gone faster with a more streamlined database. As mentioned in the Challenges-section; one endpoint (Autocomplete Ingredient Search) lacked the id-variable, making it impossible to link it to most other endpoints (except the endpoint that searches Recipes based on an ingredients name). Because of that the IngredientDetailActivity doesnt have all of detailed information if you go there straight from the Main window. Perhaps this could have been avoided with a better API. But I was already too deep into the project to change sources halfway.
Lastly, a few stylistic touches were added in time (headers for the lists, spinners for progressbars). But the app is not very pretty to look at. Its not confusing, but I would have liked to add better backgrounds and get more creative with the spinners.







