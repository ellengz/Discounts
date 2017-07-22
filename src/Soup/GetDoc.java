package Soup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by ellen on 22/7/17.
 */
public class GetDoc {

    String title;

    public GetDoc(String url) {

        if(checkURL(url)) {
            try {
                Document doc = Jsoup.connect(url).get();
                title = doc.title();

                System.out.print("The site title is: " + title);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean checkURL(String url){

        return true;

    }
}
