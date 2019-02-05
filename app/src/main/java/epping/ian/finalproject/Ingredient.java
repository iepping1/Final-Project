package epping.ian.finalproject;

import java.io.Serializable;

public class Ingredient implements Serializable {

    //create class for all menu fields
    private String name, image, id, amount;

    //store the fields of the menu
    public Ingredient(String id, String name, String image, String amount) {
    //public Ingredient(String name) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    // retrieve the fields
    String getId() { return id; }

    String getName() { return name; }

    String getImageURL() { return image; }

    String getAmount() { return amount; }

    public void setName(String name) { this.name = name; }

    public void setAmount(String amount ) { this.amount = amount; }

    public void setImage(String imageURL) {this.image = imageURL; }
}