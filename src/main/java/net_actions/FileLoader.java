package net_actions;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Jack on 27.10.2016.
 */
public class FileLoader {

    public static void fileLoad(URL url, String pathForSave) throws IOException {

        File file = new File(pathForSave);
        //check file present, create new if not
        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
             OutputStream os = new FileOutputStream(pathForSave)) {

            byte[] buff = new byte[8192];
            int count = 0;

            while ((count = bis.read(buff)) != -1) {
                os.write(buff, 0, count);
                os.flush();

            }
        }
    }

    public URL toURL(String strURL) throws URISyntaxException, MalformedURLException {
        return new URI(strURL).toURL();
    }
}
