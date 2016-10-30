package net_actions.client_server.client.vacancy_parser;

import java.io.IOException;
import java.net.URL;

import static net_actions.client_server.client.vacancy_parser.VacancyParser.getAllLoadLinksFromURLVacancy;

/**
 * Created by Jack on 30.10.2016.
 */
public class TestVacancyParser {
    public static void main(String[] args) throws IOException {
        URL testVacancyURL = new URL("https://www.work.ua/jobs-kyiv-java");

        getAllLoadLinksFromURLVacancy(testVacancyURL);
    }
}
