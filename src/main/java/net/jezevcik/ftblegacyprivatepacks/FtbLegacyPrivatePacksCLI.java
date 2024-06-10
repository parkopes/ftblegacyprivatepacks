package net.jezevcik.ftblegacyprivatepacks;

import org.apache.commons.cli.*;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URISyntaxException;

public class FtbLegacyPrivatePacksCLI {

    public static void main(String[] args) {
        final Options options = new Options();

        options.addRequiredOption("code", "modpackCode", true, "Modpack code");
        options.addOption("mods", "printMods", false, "Display mods");

        options.addOption("downloadCl", "downloadClient", true, "Download client");
        options.addOption("downloadSr", "downloadServer", true, "Download server");

        final CommandLineParser parser = new DefaultParser();
        try {
            final CommandLine cmd = parser.parse(options, args);

            final String code = cmd.getOptionValue("code");

            final FtbLegacyPrivatePacks.Modpack modpack = FtbLegacyPrivatePacks.fetch(code);

            if (modpack == null)
                throw new NullPointerException("Error parsing, modpack returned null");

            if (cmd.hasOption("downloadCl")) {
                final String version = cmd.getOptionValue("downloadCl");

                if (version == null)
                    throw new NullPointerException("Version returned null!");

                if (version.equals("latest"))
                    System.out.println(modpack.getClientUrl(modpack.latestVersion()));
                else
                    System.out.println(modpack.getClientUrl(version));
            } else if (cmd.hasOption("downloadSr")) {
                final String version = cmd.getOptionValue("downloadSr");

                if (version == null)
                    throw new NullPointerException("Version returned null!");

                if (version.equals("latest"))
                    System.out.println(modpack.getServerUrl(modpack.latestVersion()));
                else
                    System.out.println(modpack.getServerUrl(version));
            } else {
                final boolean modsOut = cmd.hasOption("mods");

                if (modsOut) {
                    System.out.printf("Mods: \n%s", ParseUtils.formatHtml(modpack.mods()));
                } else {
                    System.out.printf("Name: %s\n", modpack.name());
                    System.out.printf("Author: %s\n", modpack.author());
                    System.out.printf("Description: %s\n", modpack.description());
                    System.out.printf("Client download: %s\n", modpack.getClientUrl("version"));
                    System.out.printf("Server download: %s\n", modpack.getServerUrl("version"));
                    System.out.printf("Minecraft version: %s\n", modpack.mcVersion());
                    System.out.printf("Available versions: %s\n", String.join(",", modpack.allVerions()));
                }
            }
        } catch (ParseException | URISyntaxException | IOException | XMLStreamException e) {
            System.err.printf("Error: %s \n\n", e.getMessage());
            System.err.println("Proper usage: ");
            System.err.println("Printing information: bin.jar -code MODPACK_ID");
            System.err.println("Downloading client: bin.jar -code MODPACK_ID -downloadCl latest/version");
            System.err.println("Downloading server: bin.jar -code MODPACK_ID -downloadSr latest/version");

            System.exit(1);
        }
    }

}
