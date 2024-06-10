package net.jezevcik.ftblegacyprivatepacks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class NetUtils {

    public static String read(String urlPath) throws URISyntaxException, IOException {
        final URL url = new URI(urlPath).toURL();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buf = new byte[8192];

        try(InputStream inputStream = url.openStream()) {
            int len;
            while ((len = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
        }

        return baos.toString();
    }

}
