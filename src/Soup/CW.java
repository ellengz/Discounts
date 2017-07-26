package Soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ellen on 25/7/17.
 */
public class CW {

    String content;

    public CW(){

        this.content = getContent("http://www.chemistwarehouse.com.au/Shop-Online/665/SkinCare");

    }

    public static String getContent(String url){
        String content="";
        try {
            System.out.println("time=====start");
            Date startDate=new Date();
            Document doc= Jsoup.connect(url)
                    .data("jquery", "java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(50000)
                    .get();
            Date endDate=new Date();
            Long time=endDate.getTime()-startDate.getTime();
            System.out.println("Connecting time=="+time);
            System.out.println("time=====end");
            content=doc.toString();
            System.out.println(doc.title());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(content);
        return content;
    }

    /**
     * find product info using class "product"
     */
    public void getProducts(){

        Document doc = Jsoup.parse(content);

        Elements list = doc.getElementsByClass("Product");
        //System.out.println(list.size());

        // product sku is separated from class product
        // TODO: 25/7/17 not sure if sku is needed

        // product info
        for(Element product : list){
            String name = product.select(".product-name").text();
            String price = getPrice(product.select(".Price").text());
            String save = "0";
            if(product.select(".Save").size() != 0){
                save = getPrice(product.select(".Save").text());
            }
            //String save = getPrice(product.select(".Save").text());
            String image = product.select(".product-image").select("img").attr("src");

            // create a product item
            Item item = new Item(name, price, save, image);
            //System.out.println(name + "     " + price + "     " + image);
            System.out.println(item.toString());

        }

    }


    /**
     * only show products with special price
     */
    public void getSpecials(){

        Document doc = Jsoup.parse(content);
        ArrayList<Item> items = new ArrayList<>();
        Elements products = doc.getElementsByClass("Product");
        for(Element product : products){
            // check if product has special price
            if(product.select(".Save").size() != 0){
                String name = product.select(".product-name").text();
                String price = getPrice(product.select(".Price").text());
                String save = getPrice(product.select(".Save").text());
                String image = product.select(".product-image").select("img").attr("src");

                Item item = new Item(name, price, save, image);
                items.add(item);
                //System.out.println(item.toString());
            }

        }

        // write to a CSV file
        writeToCSV(items);

    }

    /**
     * get the price from a description (String)
     * @param str
     * @return the price
     */
    public String getPrice(String str){
        return str.replaceAll("[^\\.0123456789]","");
    }

    public void writeToCSV(ArrayList<Item> items){

        PrintWriter pw = null;
        try {

            System.out.println("processing...");

            pw = new PrintWriter(new File("products.csv"));
            StringBuilder sb = new StringBuilder();

            sb.append("name");  sb.append(',');
            sb.append("price"); sb.append(',');
            sb.append("save"); sb.append(',');
            sb.append("image"); sb.append('\n');

            for(Item item : items){

                sb.append(item.getName());  sb.append(',');
                sb.append(item.getPrice()); sb.append(',');
                sb.append(item.getSave()); sb.append(',');
                sb.append(item.getImage()); sb.append('\n');

            }

            pw.write(sb.toString());
            pw.close();
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
