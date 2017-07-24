package TryOut;

/**
 * Created by ellen on 24/7/17.
 */
public class Main {

    public static void main(String[] args) {

        String url = "http://list.jd.com/list.html?cat=737,794,798&ev=4155_79635&sort=sort_rank_asc&trans=1&JL=3_%E7%94%B5%E8%A7%86%E7%B1%BB%E5%9E%8B_4K%E8%B6%85%E6%B8%85%E7%94%B5%E8%A7%86#J_crumbsBar";

        JD jd = new JD(url);
        jd.getProduct();
    }
}
