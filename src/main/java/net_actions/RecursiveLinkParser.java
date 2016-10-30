package net_actions;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jack on 29.10.2016.
 */
public class RecursiveLinkParser {

    public static final String GLOBAL_ROOT_URL = "http://www.ex.ua";

    public static List<String> getAllLoadLinksFromURLREC(URL url) throws IOException {
        List<String> allLinksList = new ArrayList<>();

        Document document = Jsoup.parse(url, 4096);

        Element element = document.body();
        //all links
        Elements elements = element.select("a[href]");

        for (Element el : elements) {
            if (el.attr("href").endsWith("/")) {
                continue;
            }
            if (el.attr("href").equals(GLOBAL_ROOT_URL + el.attr("href"))) {
                continue;
            }
//            if(allLinksList.size() >=50){
//                return allLinksList;
//            }
            if (!el.toString().contains("http") && !el.toString().contains("https") &&
                    !el.toString().contains("user") && !el.toString().contains("#")
                    && !el.toString().contains("/ru") && !el.toString().contains("search")
                    && !el.toString().contains("login") && !el.toString().contains("javascript")
                    && !el.toString().contains("javascript") && !el.toString().contains("copyright")
                    && !el.toString().contains("contact")&& !el.toString().contains("link_id=8")) {
                //&&!el.toString().substring(1).isEmpty()
                System.out.println(GLOBAL_ROOT_URL + el.attr("href"));

                allLinksList.add(el.attr("href"));

                //allLinksList.addAll(allLinksList.size(), getAllLoadLinksFromURLREC(new URL(GLOBAL_ROOT_URL + el.attr("href"))));

            }
        }
        System.out.println("Всего найденно ссылок на данной странице: " + allLinksList.size());
        for (int i = 0; i < allLinksList.size() ; i++) {
            allLinksList.addAll(getAllLoadLinksFromURLREC(new URL(GLOBAL_ROOT_URL + allLinksList.get(i))));
        }

        return allLinksList;
    }

    public static boolean checkMatch(String str) {
        Pattern p = Pattern.compile("^[^(http)].+$");
        return p.matcher(str).matches();
    }

}
