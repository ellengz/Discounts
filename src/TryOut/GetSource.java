package TryOut;

/**
 * Created by ellen on 21/7/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class GetSource {

    public static void main(String[] args) {
        // link to visit
        String url = "https://www.woolworths.com.au/shop/browse/specials/";
        // store the source of the site
        String result = "";
        BufferedReader in = null;
        try {
            // convert String to URL
            URL realUrl = new URL(url);
            // initialize a URL connection
            URLConnection connection = realUrl.openConnection();
            // connecting
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
        } catch (Exception e) {
            System.out.println("Exception" + e);
            e.printStackTrace();
        } // close the input stream
        finally {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
    }
}
