package at.cgsit.train.java.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * lädt ein image von http url
 * und speichert in ein file
 */
public class ImageDownloader {

    public static void downloadImage(String imageUrl, String destinationFile)
            throws IOException, URISyntaxException {
        // 1. Erstellen eines URL-Objekts
        URL url = new URI(imageUrl).toURL();

        // 2. oeffnen des InputStreamL
        try (InputStream is = url.openStream();
             // 3. lokale datei öffnen
             FileOutputStream fos = new FileOutputStream(destinationFile)) {

            // Puffer für das Lesen der Daten
            byte[] buffer = new byte[4096]; // Puffergröße von 4KB
            int bytesRead;

            // 4. Lesen der Daten vom InputStream und Schreiben in den OutputStream
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Bild erfolgreich gespeichert: " + destinationFile);

        } catch (IOException e) {
            System.err.println("Fehler beim Downlaod des Bildes: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        String imageToDownload = "https://picsum.photos/200/200.jpg"; // **ERSETZEN Sie DIES** durch die tatsächliche URL

        String tempDir = System.getProperty("java.io.tmpdir");
        String targetPath = tempDir + File.separator + "temp_downloaded_image.jpg";

        try {
            downloadImage(imageToDownload, targetPath);
        } catch (Exception e) {
            System.err.println("error" + e.getMessage());
        }
    }
}