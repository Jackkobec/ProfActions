package net_actions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 29.10.2016.
 */
public class RecursiveLinkParser {

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
            if (!el.toString().contains("http") && !el.toString().contains("https") &&
                    !el.toString().contains("user") && !el.toString().contains("#")
                    && !el.toString().contains("/ru") && !el.toString().contains("search")
                    && !el.toString().contains("login") && !el.toString().contains("javascript")
                    && !el.toString().contains("javascript") && !el.toString().contains("copyright")
                    && !el.toString().contains("contact")) {
                //&&!el.toString().substring(1).isEmpty()
                //System.out.println(url + el.attr("href"));
                allLinksList.add(el.attr("href"));
                // System.out.println("element: " + el.attr("href"));
                getAllLoadLinksFromURLREC(new URL(url + el.attr("href")));

            }
            allLinksList.add(el.attr("href"));
//            if (el.getElementsContainingText("view_comments")) { //&& !(el.toString().contains("https"))
//                //&& !(el.toString().contains("http://www.ex.ua/28733?r=23778"))) {
//                // getAllLoadLinksFromURLREC(new URL(url + el.toString()));
//
//            }

        }

        // allLinksList.forEach(i -> System.out.println("Ссылка: " + i));
        //System.out.println("========================");
        System.out.println(allLinksList.size());
        return allLinksList;
    }
}
