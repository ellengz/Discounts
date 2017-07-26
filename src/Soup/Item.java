package Soup;

/**
 * Created by ellen on 25/7/17.
 */
public class Item {

    private String name;
    private Float price;
    private Float save;
    private String image;


    public Item(String name, String price, String save, String image){

        this.name = name;
        this.price = Float.valueOf(price);
        this.save = Float.valueOf(save);
        this.image = image;

    }

    public String getName(){
        return name;
    }
    public String getPrice(){
        return price.toString();
    }
    public String getSave(){ return save.toString();}
    public String getImage(){
        return image;
    }

    public String toString(){
        return name + "     " + String.format("%.2f", price) + "       " +
                String.format("%.2f", save) + "       " + image;
    }
}
