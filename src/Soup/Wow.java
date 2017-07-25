package Soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;

/**
 * Created by ellen on 25/7/17.
 */
public class Wow {

    static Document document;

    public Wow() throws IOException {

        String url = "https://www.woolworths.com.au/shop/browse/specials/health-beauty-sale";
        document = getDoc(url);
        //System.out.print(document.toString());
    }

    public void printProductName(){

        Elements elements = document.getElementsByTag("span");
        int i = 0;
        for(Element em : elements){
            i++;
        }
        System.out.println(i);


    }


    private Document getDoc(String url) throws IOException {

        return Jsoup.connect(url).get();

    }

}
