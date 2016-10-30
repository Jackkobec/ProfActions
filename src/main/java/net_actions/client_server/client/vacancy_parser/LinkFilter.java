package net_actions.client_server.client.vacancy_parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jack on 30.10.2016.
 */
public class LinkFilter {


    public static final String COMPANY = "company";
    public static final String SETLP = "setlp";
    public static final String RESOURCES = "resources/";
    public static final String CREATE = "create";
    public static final String LOGIN = "login";

    public LinkFilter() {
    }


    public boolean linkFilter(String forFil) {
        if (forFil.contains(SETLP)|| forFil.contains(COMPANY)
                || forFil.contains(RESOURCES) || forFil.contains(CREATE)
                || forFil.contains(LOGIN)) {
            return true;

        }
        return false;
    }

}
