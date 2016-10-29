package net_actions;

import java.io.IOException;
import java.net.URL;

import static net_actions.FileLoader.fileLoad;
import static net_actions.LinkParser.getAllLinksFromURL;
import static net_actions.LinkParser.getAllLinksWithRegExFromURL;
import static net_actions.LinkParser.getAllLoadLinksFromURL;
import static net_actions.RecursiveLinkParser.getAllLoadLinksFromURLREC;

/**
 * Created by Jack on 27.10.2016.
 */
public class TestNetActions {
    public static void main(String[] args) throws IOException {
        URL testUrl = new URL("http://www.ex.ua/105384412?r=16984,23777");
        URL testImgUrl = new URL("http://www.ex.ua/load/280810532");
        URL testDocURL = new URL("http://www.ex.ua/28733?r=23778");

        String PathToSave = "src/main/resources/newFile.jpeg";
        //getAllLinksFromURL(testDocURL);

        // getAllLoadLinksFromURL(testUrl);

        //getAllLinksWithRegExFromURL(testUrl);

        //fileLoad(testImgUrl, PathToSave);
        //System.out.println(trimmer("   fefef     ee  vev "));

       getAllLoadLinksFromURLREC(testDocURL).forEach(i -> System.out.println("Ссылка: " + i));
    }
}
