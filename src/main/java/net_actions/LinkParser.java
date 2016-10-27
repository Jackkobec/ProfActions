package net_actions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Jack on 27.10.2016.
 */
public class LinkParser {
    public static final String PATTERN_FOR_A_TEG = "[(?i)<a([^>]+)>(.+?)</a>]";
    public static final String PATTERN_FOR_HREF = "[\\s*(?i)href\\s*=\\s*(\\\"([^\"]*\\\")|'[^']*'|([^'\">\\s]+))]";
    private static final String SPACE_PATTERN = "[[\\s]+]";






    public static void getAllLinksFromURL(URL url) throws IOException {
        List<String> allLinksList = new ArrayList<>();

        Document document = Jsoup.parse(url, 1024);

        Element element = document.body();
        //all links
        Elements elements = element.select("a[href]");

        for (Element el : elements) {
            allLinksList.add(el.attr("href"));
        }
        allLinksList.forEach(i -> System.out.println("Ссылка: " + i));
    }


    public static void getAllLoadLinksFromURL(URL url) throws IOException {
        List<String> loadLinksList = new ArrayList<>();
        List<String> LoadAltLinksList = new ArrayList<>();

        Document document = Jsoup.parse(url, 1024);

        Element element = document.body();
        //links begins with /load
        Elements elementsLoad = element.select("a[href^=/load]");

        for (Element el : elementsLoad) {
            if (!(el.toString().contains("?"))) {
                loadLinksList.add(el.attr("href"));
            } else {
                LoadAltLinksList.add(el.attr("href"));
            }
        }
        loadLinksList.forEach(i -> System.out.println("Ссылка с /load: " + i));
        LoadAltLinksList.forEach(i -> System.out.println("Альтернативная ссылка с /load: " + i));
    }

    public static void getAllLinksWithRegExFromURL(URL url) throws IOException {
        List<String> allLinksList = new ArrayList<>();

        Document document = Jsoup.parse(url, 1024);
        Element element = document.body();
        Elements elements = element.getElementsMatchingOwnText(PATTERN_FOR_HREF);
        for (Element el : elements) {
            if (!(null == el)) {
                allLinksList.add(el.attr("href"));
            } else continue;

        }
        allLinksList.forEach(i -> System.out.println("Ссылка: " + i));
    }

    public static String trimmer(String str) {
        final Pattern CLEAR_PATTERN = Pattern.compile("[\\s]+");
        return CLEAR_PATTERN.matcher(str).replaceAll(" ").trim();
    }
}

