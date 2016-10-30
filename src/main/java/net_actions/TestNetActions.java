package net_actions;

import java.io.IOException;
import java.net.URL;

import static net_actions.client_server.client.vacancy_parser.VacancyParser.getAllLoadLinksFromURLVacancy;

/**
 * Created by Jack on 27.10.2016.
 */
public class TestNetActions {
    public static void main(String[] args) throws IOException {
        URL testUrl = new URL("http://www.ex.ua/105384412?r=16984,23777");
        URL testImgUrl = new URL("http://www.ex.ua/load/280810532");
        URL testDocURL = new URL("http://www.ex.ua/28733?r=23778");
        URL testVacancyURL = new URL("https://www.work.ua/jobs-kyiv-java");

        String PathToSave = "src/main/resources/newFile.jpeg";
        //getAllLinksFromURL(testDocURL);

        // getAllLoadLinksFromURL(testUrl);

        //getAllLinksWithRegExFromURL(testUrl);

        //fileLoad(testImgUrl, PathToSave);
        //System.out.println(trimmer("   fefef     ee  vev "));

       //getAllLoadLinksFromURLREC(testDocURL).forEach(i -> System.out.println("Ссылка: " + i));

        getAllLoadLinksFromURLVacancy(testVacancyURL);
    }
}
