# Your Diet's Best Friend!
           Ian Epping
## Samenvatting
In deze app kan je recepten en ingredienten bekijken, beoordelen en dat vergelijken met je eigen dieet.
- Probleem
Het bijhouden van hoeveel je op een dag eet, en wat je wel of niet mag hebben, is vaak erg lastig. Een goede maaltijd is wel iets wat je elke dag nodig hebt.
Je kent vast wel wat recepten die je bevallen, maar je wilt niet elke dag hetzelfde eten. En he wilt niet te veel tijd verspillen in een winkel door naar elke label te kijken of het wel of niet goed voor je is.

- Oplossing
Als je een app had, die talloze recepten kan ophalen en analyzeren aan de hand van hun inhoud en calorieen. Dan weet je van vooraf al of een recept die je interreseert goed is.
Je hoeft alleen maar een paar zoekwoorden of parameters te gebruiken/kaders door te zoeken, en je hebt jouw antwoord. Het zoekt ook uit of een recept glutenvrij en/of vegetarisch is!

- Product Demo Link: https://www.youtube.com/watch?v=KIxKFMXYup4

## Generale Opzet
- Main Activity (Kiezen tussen ingredienten via trefwoorden, recepten via calorieen of willekeurige recepten)
- Recipe Actitity/Adapter & RecipeRequest (Laat een aantal resultaten zien aan de hand van de hoeveelheid calorieen die je in je dieet niet mag overschrijdenen stops ze allemaal in een lijst)
- Random Activity/Adapter & RandomRequest (Maakt een lijst van een aantal willekeurige recepten met informatie over de gluten inhoud en of het wel of niet vegetarisch is. Zowel deze activiteit en de recept activiteit schakelen door naar...)
- Recipe Detail Activity (Het recept zelf met zo veel mogelijk info over de gekozen recept inclusief instructies. Plus een doorschakeling naar specifieke ingredienten en de mogelijkheid om terug naar start te gaan.)
- Recipe Detail Request (In het geval van RecipeActivity is er een 2e request nodig om verdere info te krijgen van het recept, via een andere api. De Id in git geval maakt een link aan tussen de twee APIs)
- Recept (De klasse voor het recept item. Bevat velden voor naam, id, image, gluten/vegetarian values en instructies.)
- Ingredient Activity/Adapter/AutoIngredientRequest (hetzelfde principe als de recepten pagina, alleen je zoekt nu specifieke ingredienten)
- Ingredient Detail Activity & Ingredient Detail Request (alleen ingredienten gevonden via een recept kunnen extra informatie tonen dankzij een minpunt in de gekozen API, dus is er alleen een detail pagina voor ingredienten die werden gekozen via de recipe detail activity)
- Ingredient Item (Alle info over het ingredient. Via de naam van het recept kan er een link gemaakt worden naar recepten via een ingredient-zoekende api)

- Ingredient Activity/Adapter/AutoIngredientRequest (hetzelfde principe als de recepten pagina, alleen je zoekt nu specifieke ingredienten)
- Ingredient Detail Activity & Ingredient Detail Request (alleen ingredienten gevonden via een recept kunnen extra informatie tonen dankzij een minpunt in de gekozen API, dus is er alleen een detail pagina voor ingredienten die werden gekozen via de recipe detail activity)
- Ingredient Item (Alle info over het ingredient. Via de naam van het recept kan er een link gemaakt worden naar recepten via een ingredient-zoekende api)

### Data Bron
> https://rapidapi.com/spoonacular/api/recipe-food-nutrition - Een site die alle voedsel informatie van spoonacular vrijgeeft als JSONbjects or Arrays.

Ik had nog een vergelijkbare apps bekeken. Maar ik wilde niet te veel beinvloed worden door ander werk van vooraf.

Het moelijkste deel was de 'kwaliteit'  berekening van elk recept zijn. Het ligt niet alleen van de ingredienten af, maar ook van de hoeveelheid die je ervan nodig hebt.
Helaas heb ik uiteindelijk deze implementatie niet kunnen voltooien en gaat het nu alleen nog om een vaste waarde input.
