package Soup;

/**
 * Created by ellen on 22/7/17.
 */
public class Main {

    public static void main(String[] args) {

        String url = "https://www.woolworths.com.au/shop/browse/specials/health-beauty-sale";
        GetDoc getDoc = new GetDoc(url);
        String divContent = getDoc.getDivContent("shop-content");


    }
}
