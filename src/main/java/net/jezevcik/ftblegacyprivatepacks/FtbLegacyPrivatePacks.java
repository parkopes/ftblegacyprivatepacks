package net.jezevcik.ftblegacyprivatepacks;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;

public class FtbLegacyPrivatePacks {

    public static final String MODPACK_INFO_TEMPLATE = "https://dist.creeper.host/FTB2/static/%s.xml",
                            PACK_URL_TEMPLATE = "https://dist.creeper.host/FTB2/privatepacks/%s/%s/%s";

    public static Modpack fetch(String code) throws URISyntaxException, IOException, XMLStreamException {
        final String modpackInformation = String.format(MODPACK_INFO_TEMPLATE, code);
        final String read = NetUtils.read(modpackInformation);

        final XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(read));

        while (xmlStreamReader.hasNext()) {
            if (xmlStreamReader.getEventType() == 1 && xmlStreamReader.getLocalName().equals("modpack")) {
                final String author = xmlStreamReader.getAttributeValue(0),
                        description = xmlStreamReader.getAttributeValue(1),
                        dir = xmlStreamReader.getAttributeValue(2),
                        mcVersion = xmlStreamReader.getAttributeValue(5),
                        mods = xmlStreamReader.getAttributeValue(6),
                        name = xmlStreamReader.getAttributeValue(7),
                        latestVersion = xmlStreamReader.getAttributeValue(9),
                        serverDownload = xmlStreamReader.getAttributeValue(10),
                        clientDownload = xmlStreamReader.getAttributeValue(11);
                final String[] allVersions = xmlStreamReader.getAttributeValue(8).split(";");

                return new Modpack(code, author, description, dir, mcVersion, mods, name, allVersions, latestVersion, serverDownload, clientDownload);
            }

            xmlStreamReader.next();
        }

        return null;
    }

    public record Modpack(String code, String author, String description, String dir,
                          String mcVersion, String mods, String name, String[] allVerions, String latestVersion,
                          String serverDownload, String clientDownload) {

        public String getClientUrl(String version) {
            return String.format(PACK_URL_TEMPLATE, dir, version.replace(".", "_"), clientDownload);
        }

        public String getServerUrl(String version) {
            return String.format(PACK_URL_TEMPLATE, dir, version.replace(".", "_"), serverDownload);
        }

    }

}
