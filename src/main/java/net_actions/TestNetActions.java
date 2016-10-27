package net_actions;

import java.io.IOException;
import java.net.URL;

import static net_actions.FileLoader.fileLoad;
import static net_actions.LinkParser.getAllLinksFromURL;
import static net_actions.LinkParser.getAllLinksWithRegExFromURL;
import static net_actions.LinkParser.getAllLoadLinksFromURL;

/**
 * Created by Jack on 27.10.2016.
 */
public class TestNetActions {
    public static void main(String[] args) throws IOException {
        URL testUrl = new URL("http://www.ex.ua/105384412?r=16984,23777");
        URL testImgUrl = new URL("http://www.ex.ua/load/280810532");

        String PathToSave = "src/main/resources/newFile.jpeg";
        getAllLinksFromURL(testUrl);

        getAllLoadLinksFromURL(testUrl);

        //getAllLinksWithRegExFromURL(testUrl);

        fileLoad(testImgUrl, PathToSave);
        //System.out.println(trimmer("   fefef     ee  vev "));
    }
}
