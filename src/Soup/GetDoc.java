package Soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by ellen on 22/7/17.
 */
public class GetDoc {

    static Document doc;
    static String url;
    static String content;


    /**
     * Create a jsoup document from the url
     * @param url
     */
    public GetDoc(String url) {

        this.url = url;
        this.getContent();

    }

    /**
     * Check whether url is legal
     * @param url
     * @return true if the url is legal
     */
    public boolean checkURL(String url){
        return true;
    }

    /**
     * Print the site title
     */
    public void getContent(){

        if(checkURL(url)) {
            try {
                doc = Jsoup.connect(url).get();
                content = doc.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDivContent(String className){

        String divContent = "";
        Elements divs = doc.getElementsByClass(className);
        divContent=divs.toString();
        System.out.println("div==="+divContent);
        return divContent;

    }
}
