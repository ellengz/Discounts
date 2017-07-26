package Soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
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
        Elements products = doc.getElementsByClass("Product");
        for(Element product : products){
            // check if product has special price
            if(product.select(".Save").size() != 0){
                String name = product.select(".product-name").text();
                String price = getPrice(product.select(".Price").text());
                String save = getPrice(product.select(".Save").text());
                String image = product.select(".product-image").select("img").attr("src");

                Item item = new Item(name, price, save, image);
                System.out.println(item.toString());
            }

        }

    }

    /**
     * get the price from a description (String)
     * @param str
     * @return the price
     */
    public String getPrice(String str){
        return str.replaceAll("[^\\.0123456789]","");
    }

}
