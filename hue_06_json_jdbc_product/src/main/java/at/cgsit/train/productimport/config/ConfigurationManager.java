package at.cgsit.train.productimport.config;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * Lädt die Konfiguration aus mehreren Quellen in festgelegter Reihenfolge:
 *
 * 1) Defaults aus Classpath (app-defaults.properties)
 * 2) Externe Datei im Arbeitsverzeichnis (app.properties)
 * 3) Environment-Variablen (DB_URL, APP_MODE, ...)
 * 4) Programm-Argumente (--mode=..., --dbUrl=...)
 */
public class ConfigurationManager {

    /**
     * es ist immer gut die configurations parameter und den file namen zentral dokumentiert zu haben
     * am besten hier auch noch die Beschreibung wofür sie zuständig sind
     */
    public static final String APP_CONFIG_FILENAME = "app-defaults.properties";
    public static final String CONFIG_DB_URL = "dbUrl";
    public static final String CONFIG_DB_URL_FROM_ENV = "DB_URL";
    public static final String CONFIG_DB_USER = "dbUser";
    public static final String CONFIG_DB_PASSWORD = "dbPassword";
    public static final String CONFIG_MODE = "mode";
    public static final String CONFIG_INPUT = "input";
    public static final String CONFIG_ONLY_ACTIVE = "onlyActive";
    public static final String CONFIG_MIN_PRICE = "minPrice";
    public static final String CONFIG_MIN_PRICE_ENV = "MIN_PRICE";
    public static final String CONFIG_ONLY_ACTIVE_ENV = "ONLY_ACTIVE";
    public static final String CONFIG_APP_MODE_ENV = "APP_MODE";
    public static final String CONFIG_DB_PASSWORD_ENV = "DB_PASSWORD";
    public static final String CONFIG_DB_USER_ENV = "DB_USER";

    public AppConfig load(String[] args) {
        Map<String, String> defaultProps = loadDefaultsFromClasspath();
        Map<String, String> fileProps = loadExternalProperties();
        Map<String, String> envMap      = loadEnv();
        Map<String, String> argMap      = parseArgs(args);

        // DB-Konfiguration
        String dbUrl = firstNonEmpty(
                argMap.get(CONFIG_DB_URL),
                envMap.get(CONFIG_DB_URL_FROM_ENV),
                fileProps.get(CONFIG_DB_URL),
                defaultProps.get(CONFIG_DB_URL),
                "jdbc:h2:./productdb"
        );

        String dbUser = firstNonEmpty(
                argMap.get(CONFIG_DB_USER),
                envMap.get(CONFIG_DB_USER_ENV),
                fileProps.get(CONFIG_DB_USER),
                defaultProps.get(CONFIG_DB_USER),
                "sa"
        );

        String dbPassword = firstNonEmpty(
                argMap.get(CONFIG_DB_PASSWORD),
                envMap.get(CONFIG_DB_PASSWORD_ENV),
                fileProps.get(CONFIG_DB_PASSWORD),
                defaultProps.get(CONFIG_DB_PASSWORD),
                ""
        );

        // Mode (import / list)
        String mode = firstNonEmpty(
                argMap.get(CONFIG_MODE),
                envMap.get(CONFIG_APP_MODE_ENV),
                fileProps.get(CONFIG_MODE),
                defaultProps.get(CONFIG_MODE),
                "list"
        );

        // Input-File (wichtig für Import)
        String inputFile = firstNonEmpty(
                argMap.get(CONFIG_INPUT),
                fileProps.get(CONFIG_INPUT),
                defaultProps.get(CONFIG_INPUT)
        );

        // optionale Filter
        Boolean onlyActive = Optional.ofNullable(firstNonEmpty(
                        argMap.get(CONFIG_ONLY_ACTIVE),
                        envMap.get(CONFIG_ONLY_ACTIVE_ENV),
                        fileProps.get(CONFIG_ONLY_ACTIVE),
                        defaultProps.get(CONFIG_ONLY_ACTIVE)
                ))
                .map(Boolean::valueOf)
                .orElse(null);

        BigDecimal minPrice = Optional.ofNullable(firstNonEmpty(
                        argMap.get(CONFIG_MIN_PRICE),
                        envMap.get(CONFIG_MIN_PRICE_ENV),
                        fileProps.get(CONFIG_MIN_PRICE),
                        defaultProps.get(CONFIG_MIN_PRICE)
                ))
                .map(BigDecimal::new)
                .orElse(null);

        return new AppConfig(
                dbUrl,
                dbUser,
                dbPassword,
                mode,
                inputFile,
                onlyActive,
                minPrice
        );
    }

    // ---------- Classpath-Defaults: app-defaults.properties ----------

    private Map<String, String> loadDefaultsFromClasspath() {
        Map<String, String> result = new HashMap<>();

        try (InputStream in = ConfigurationManager.class
                .getClassLoader()
                .getResourceAsStream(APP_CONFIG_FILENAME)) {

            if (in == null) {
                return result; // keine Defaults definiert
            }
            Properties props = new Properties();
            props.load(in);
            for (String name : props.stringPropertyNames()) {
                result.put(name, props.getProperty(name));
            }
        } catch (IOException e) {
            System.err.println("Konnte app-defaults.properties nicht laden: " + e.getMessage());
        }
        return result;
    }

    // ---------- Externe Datei: app.properties im Arbeitsverzeichnis ----------

    private Map<String, String> loadExternalProperties() {
        Map<String, String> result = new HashMap<>();

        exkursShowCurrentJavaStandardDirs();

        Path path = Path.of(APP_CONFIG_FILENAME);
        if (!Files.exists(path)) {
            return result;
        }

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(path)) {
            props.load(in);
            for (String name : props.stringPropertyNames()) {
                result.put(name, props.getProperty(name));
            }
        } catch (IOException e) {
            System.err.println("Konnte app.properties nicht laden: " + e.getMessage());
        }
        return result;
    }

    /**
     * zeigt die standards von java. ein config file könne auch im user dir liegen.
     * das bei server installationen auch oftmals geschützt ist (wegen credentals etc)
     */
    private static void exkursShowCurrentJavaStandardDirs() {
        // Das aktuelle Arbeitsverzeichnis abrufen
        String workingDir = System.getProperty("user.dir");
        System.out.println("Aktuelles Arbeitsverzeichnis (user.dir): " + workingDir);

        // oder NEU Paths.get("") erzeugt einen relativen Pfad zum CWD (current working directory)
        Path currentPath = Paths.get("").toAbsolutePath();
        System.out.println("Aktuelles Arbeitsverzeichnis (NIO): " + currentPath.toString());

        System.out.println("Home-Verzeichnis (user.home): " + System.getProperty("user.home"));
        System.out.println("Java-Verzeichnis (java.home): " + System.getProperty("java.home"));
        System.out.println("Pfadtrennzeichen (file.separator): " + System.getProperty("file.separator"));
    }

    // ---------- Environment-Variablen ----------

    private Map<String, String> loadEnv() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> systemEnv = System.getenv();

        putIfPresent(systemEnv, env, CONFIG_DB_URL_FROM_ENV);
        putIfPresent(systemEnv, env, CONFIG_DB_USER_ENV);
        putIfPresent(systemEnv, env, CONFIG_DB_PASSWORD_ENV);
        putIfPresent(systemEnv, env, CONFIG_APP_MODE_ENV);
        putIfPresent(systemEnv, env, CONFIG_ONLY_ACTIVE_ENV);
        putIfPresent(systemEnv, env, CONFIG_MIN_PRICE_ENV);

        return env;
    }

    private void putIfPresent(Map<String, String> source,
                              Map<String, String> target,
                              String key) {
        String value = source.get(key);
        if (value != null && !value.isBlank()) {
            target.put(key, value);
        }
    }

    // ---------- CLI-Args: --key=value ----------

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        if (args == null) return map;

        for (String arg : args) {
            if (arg == null || !arg.startsWith("--")) continue;

            String withoutPrefix = arg.substring(2);
            int eqIndex = withoutPrefix.indexOf('=');
            if (eqIndex > 0) {
                String key = withoutPrefix.substring(0, eqIndex);
                String value = withoutPrefix.substring(eqIndex + 1);
                map.put(key, value);
            }
        }
        return map;
    }

    // ---------- Helfer ----------

    private String firstNonEmpty(String... values) {
        for (String v : values) {
            if (v != null && !v.isBlank()) {
                return v;
            }
        }
        return null;
    }
}
