package net_actions.client_server.client.vacancy_parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Jack on 30.10.2016.
 */
public class VacancyParser {

    public static final String GLOBAL_ROOT_URL = "https://www.work.ua";
    public static final String DAY_FILTER = "days=125"; //show for the 30 days

    public static List<String> getAllLoadLinksFromURLVacancy(URL url) throws IOException {

        List<String> allLinksList = new ArrayList<>();

        Document document = Jsoup.parse(url, 4096);

        //Element element = document.body();
        //all links
        Elements elements = document.body().select("a[href]");

        for (Element el : elements) {
            String href = el.attr("href");

            if (href.equals("/jobs/") || new LinkFilter().linkFilter(href)) {
                continue;
            }

            if (el.toString().contains("jobs")) { //&& checkMatchDigita(el.toString()
                System.out.println(GLOBAL_ROOT_URL + href);
                System.out.println();
                allLinksList.add(href);
            }
        }
        System.out.println(allLinksList.size());
        return allLinksList;
    }

    public static boolean checkMatchDigita(String str) {
        Pattern p = Pattern.compile("[0-9]");
        return p.matcher(str).matches();
    }
}
/**
 * Dev info
 * <p>
 * <p>
 * https://www.work.ua/jobs-kyiv-java/?days=125    -  for the 30 days
 * <p>
 * https://www.work.ua/ua/jobs-kyiv-java/?days=125&desc=1   - show vacancys without descreption
 * https://www.work.ua/ua/jobs-kyiv-java/?days=125&desc=1   -  show vacancys with descreption
 *
 * We will move trough the pages( example https://www.work.ua/jobs-kyiv-java/?page=4 )
 *
 **/