package epping.ian.finalproject;

import java.io.Serializable;

public class Recept implements Serializable {

    //create variables for all recipe categories
    private String name, image, instructions, content, vegetarian, gluten, recipeId;

    // constructor for recipes found with random and detailed search
    public Recept(String name, String image, String vegetarian, String gluten, String instructions, String recipeId) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.vegetarian = vegetarian;
        this.gluten = gluten;
        this.recipeId = recipeId;
    }

    // constructor for recipe found through calory search
    public Recept(String name, String image, String content, String recipeId) {
        this.name = name;
        this.image = image;
        this.content = content;
        this.recipeId = recipeId;
    }

    // retrieve the fields
    String getName() { return name; }

    String getImage() { return image; }

    String getInstructions() { return instructions; }

    String getContent() { return content; }

    String getVegetarian() { return vegetarian; }

    String getGluten() { return gluten; }

    String getRecipeId() { return recipeId; }

    public void setName(String name) { this.name = name; }

    public void setImage(String image) {this.image = image; }
}