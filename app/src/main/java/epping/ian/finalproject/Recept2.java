package epping.ian.finalproject;

import java.io.Serializable;

public class Recept2 implements Serializable {

    //create class for all recipe categories
    private String name, image, instructions, vegetarian, gluten, id;

    //store the categories of the recipe
    //public Recept(String name, String image, String instructions, String vegetarian, String gluten) {
    public Recept2(String name, String image, String id) {
        this.name = name;
        this.image = image;
        //this.instructions = instructions;
        //this.vegetarian = vegetarian;
        //this.gluten = gluten;
        this.id = id;
    }

    // retrieve the fields
    String getName() { return name; }

    String getImage() { return image; }

    String getInstructions() { return instructions; }

    String getVegetarian() { return vegetarian; }

    String getGluten() { return gluten; }

    String getRecipeId() { return id; }

    public void setName(String name) { this.name = name; }

    public void setImage(String image) {this.image = image; }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    //public void setVegetarian(String vegetarian) { this.vegetarian = vegetarian; }

    //public void setGluten(String gluten) {this.gluten = gluten; }
}