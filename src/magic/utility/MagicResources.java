package magic.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import magic.data.MagicIcon;
import magic.data.MagicPredefinedFormat;
import magic.data.MagicSets;

public final class MagicResources {
    private MagicResources() { }

    // Used as reference class for accessing JAR resources.
    private static final MagicResources instance = new MagicResources();

    public static String getFileContent(final MagicSets magicSet) {
        return getResourceFileContent("/magic/data/sets/" + magicSet.toString().replace("_", "") + ".txt");
    }

    public static String getFileContent(final MagicPredefinedFormat magicFormat) {
        return getResourceFileContent("/magic/data/formats/" + magicFormat.getFilename() + ".fmt");
    }

    public static InputStream getJarResourceStream(String filename) {
        return instance.getClass().getResourceAsStream(filename);
    }

    private static String getResourceFileContent(final String filename) {
        try (final InputStream inputStream = getJarResourceStream(filename)) {
            return inputStream != null ? FileIO.toStr(inputStream) : "";
        } catch (final IOException ex) {
            System.err.println(filename + " : " + ex.getMessage());
            return "";
        }
    }

    public static URL getManaImageUrl(MagicIcon manaIcon) {
        return instance.getClass().getResource("/magic/data/icons/mana/" + manaIcon.getFilename());
    }

    public static URL getImageUrl(final String imageFilename) {
        return instance.getClass().getResource("/magic/data/icons/" + imageFilename);
    }

    public static URL getTextureImageUrl(final String imageFilename) {
        return instance.getClass().getResource("/magic/data/textures/" + imageFilename);
    }

    public static URL getSoundUrl(final String filename) {
        return instance.getClass().getResource("/soundfx/" + filename);
    }

    public static InputStreamReader getH2ScriptFile(String filename) {
        return new InputStreamReader(
            getJarResourceStream("/h2/stats/" + filename)
        );
    }
}
