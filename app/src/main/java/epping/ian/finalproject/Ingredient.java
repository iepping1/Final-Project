package epping.ian.finalproject;

import java.io.Serializable;

public class Ingredient implements Serializable {

    // create variable for all possible ingredient fields
    private String name, image, amount, protein, fat, carbs, ingredientId;

    // constructor for ingredient found via autocomplete search
    public Ingredient(String name, String image, String amount) {
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    // constructors for ingredient found through recipes
    public Ingredient(String name, String image, String amount, String ingredientId) {
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.ingredientId = ingredientId;
    }

    // constructors for detailed ingredient information
    public Ingredient(String name, String image, String protein, String fat, String carbs, String ingredientId) {
        this.name = name;
        this.image = image;
        this.protein= protein;
        this.fat = fat;
        this.carbs = carbs;
        this.ingredientId = ingredientId;
    }

    // retrieve the fields
    String getName() { return name; }

    String getImageURL() { return image; }

    String getAmount() { return amount; }

    String getProtein() { return protein; }

    String getFat() { return fat; }

    String getCarbs() { return carbs; }

    String getId() { return ingredientId; }

    public void setName(String name) { this.name = name; }

    public void setImage(String imageURL) {this.image = imageURL; }
}