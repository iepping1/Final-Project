package epping.ian.finalproject;

import java.io.Serializable;

public class Ingredient implements Serializable {

    // create variable for all possible ingredient fields
    private String name, image, amount, protein, fat, carbs, ingredient_id;


    // constructor for ingredient drawn from main window
    public Ingredient(String name, String image, String amount) {
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    // constructors for ingredient drawn from recipe
    public Ingredient(String name, String image, String amount, String ingredient_id) {
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.ingredient_id = ingredient_id;
    }

    // constructors for ingredient detail
    public Ingredient(String name, String image, String protein, String fat, String carbs, String ingredient_id) {
        this.name = name;
        this.image = image;
        this.protein= protein;
        this.fat = fat;
        this.carbs = carbs;
        this.ingredient_id = ingredient_id;
    }

    // retrieve the fields
    String getName() { return name; }

    String getImageURL() { return image; }

    String getAmount() { return amount; }

    String getProtein() { return protein; }

    String getFat() { return fat; }

    String getCarbs() { return carbs; }

    String getId() { return ingredient_id; }

    public void setName(String name) { this.name = name; }

    public void setImage(String imageURL) {this.image = imageURL; }
}