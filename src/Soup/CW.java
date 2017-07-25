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

    public void getTable(){

        Document doc = Jsoup.parse(content);

        Elements list = doc.getElementsByClass("Product");
        System.out.println(list.size());

        // product sku is separated from class product
        // TODO: 25/7/17 not sure if sku is needed

        // product info
        for(Element product : list){
            String name = product.select(".product-name").text();
            String price = product.select(".Price").text();
            String image = product.select(".product-image").select("img").attr("src");

            // create a product item
            Item item = new Item(name, price, image);
            //System.out.println(name + "     " + price + "     " + image);

        }

    }


}
