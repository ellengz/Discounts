package TryOut;

import com.sun.deploy.util.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ellen on 24/7/17.
 */
public class JD {

    Document document;

    public JD(String url) {

        getDoc(url);

    }

    private void getDoc(String url){

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getProduct(){

        Elements ems = document.select("#plist li.gl-item");
        Map<Long, Item> items = new HashMap<>();
        for (Element em : ems) {
            // 获取id
            String id = em.select(".gl-i-wrap").attr("data-sku");
            // 获取名称
            String name = em.select(".p-name a em").text();
            // 获取图片
            String imgage = em.select(".gl-i-wrap >.p-img > a >img").attr("src")
                    .replace("//", "");

            System.out.println("id      " + id + "  name      " + name + "    image       " + imgage);

//            // 构造商品
//            Item item = new Item();
//            item.setId(Long.valueOf(id));
//            item.setTitle(name);
//            item.setImage(imgage);
//            items.put(item.getId(), item);
        }

//        // 添加id 要以这个格式 J_3466744
//        List<String> strIds = new ArrayList<String>();
//        for (Long id : items.keySet()) {
//            strIds.add("J_" + id);
//        }
//
//        // 获取商品的价格StringUtils.join([1, 2, 3], ';') = "1;2;3"
//        String priceUrl = "http://p.3.cn/prices/mgets?type=1&area=19_1607_3155_0&skuIds="
//                + StringUtils.join(strIds, ",");
//        String priceDate = doGet(priceUrl);
//        // 解析json
//        ArrayNode arrayNode = (ArrayNode) MAPPER.readTree(priceDate);
//        for (JsonNode jsonNode : arrayNode) {
//            Long id = Long.valueOf(StringUtils.substringAfter(
//                    jsonNode.get("id").asText(), "_"));
//            // 利用map 将price 回填 高明
//            Long price = jsonNode.get("p").asLong();
//            items.get(id).setPrice(price);
//        }

//        // 打印商品
//        for (Item item : items.values()) {
//            System.out.println(item.toString());
//        }
//
    }

    class Item{

        Long id;
        String name;
        String image;

        private Item(){

        }

        private void setId(Long id){
            this.id = id;
        }

        private void setTitle(String name){
            this.name = name;
        }

        private void setImage(String image){
            this.image = image;
        }

        private Long getId(){
            return this.id;
        }
    }

}
