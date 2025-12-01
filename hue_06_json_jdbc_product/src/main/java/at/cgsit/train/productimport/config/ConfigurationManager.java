package at.cgsit.train.productimport.config;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public AppConfig load(String[] args) {
        Map<String, String> defaultProps = loadDefaultsFromClasspath();
        Map<String, String> fileProps = loadExternalProperties();
        Map<String, String> envMap      = loadEnv();
        Map<String, String> argMap      = parseArgs(args);

        // DB-Konfiguration
        String dbUrl = firstNonEmpty(
                argMap.get("dbUrl"),
                envMap.get("DB_URL"),
                fileProps.get("dbUrl"),
                defaultProps.get("dbUrl"),
                "jdbc:h2:./productdb"
        );

        String dbUser = firstNonEmpty(
                argMap.get("dbUser"),
                envMap.get("DB_USER"),
                fileProps.get("dbUser"),
                defaultProps.get("dbUser"),
                "sa"
        );

        String dbPassword = firstNonEmpty(
                argMap.get("dbPassword"),
                envMap.get("DB_PASSWORD"),
                fileProps.get("dbPassword"),
                defaultProps.get("dbPassword"),
                ""
        );

        // Mode (import / list)
        String mode = firstNonEmpty(
                argMap.get("mode"),
                envMap.get("APP_MODE"),
                fileProps.get("mode"),
                defaultProps.get("mode"),
                "list"
        );

        // Input-File (wichtig für Import)
        String inputFile = firstNonEmpty(
                argMap.get("input"),
                fileProps.get("input"),
                defaultProps.get("input")
        );

        // optionale Filter
        Boolean onlyActive = Optional.ofNullable(firstNonEmpty(
                        argMap.get("onlyActive"),
                        envMap.get("ONLY_ACTIVE"),
                        fileProps.get("onlyActive"),
                        defaultProps.get("onlyActive")
                ))
                .map(Boolean::parseBoolean)
                .orElse(null);

        BigDecimal minPrice = Optional.ofNullable(firstNonEmpty(
                        argMap.get("minPrice"),
                        envMap.get("MIN_PRICE"),
                        fileProps.get("minPrice"),
                        defaultProps.get("minPrice")
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
                .getResourceAsStream("app-defaults.properties")) {

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
        Path path = Path.of("app.properties");
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

    // ---------- Environment-Variablen ----------

    private Map<String, String> loadEnv() {
        Map<String, String> env = new HashMap<>();
        Map<String, String> systemEnv = System.getenv();

        putIfPresent(systemEnv, env, "DB_URL");
        putIfPresent(systemEnv, env, "DB_USER");
        putIfPresent(systemEnv, env, "DB_PASSWORD");
        putIfPresent(systemEnv, env, "APP_MODE");
        putIfPresent(systemEnv, env, "ONLY_ACTIVE");
        putIfPresent(systemEnv, env, "MIN_PRICE");

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
