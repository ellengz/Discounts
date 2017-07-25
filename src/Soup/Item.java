package Soup;

/**
 * Created by ellen on 25/7/17.
 */
public class Item {

    String name;
    String price;
    String image;


    public Item(String name, String price, String image){

        this.name = name;
        this.price = price;
        this.image = image;

    }

    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }
    public String getImage(){
        return image;
    }
}
