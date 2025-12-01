package at.cgsit.train.productimport.config;

import java.math.BigDecimal;

/**
 * Zentrale, unveränderliche Konfiguration der Anwendung.
 */
public record AppConfig(
        String dbUrl,
        String dbUser,
        String dbPassword,
        String mode,
        String inputFile,
        Boolean onlyActive,
        BigDecimal minPrice
) {
    @Override
    public String toString() {
        return "AppConfig{" +
               "dbUrl='" + dbUrl + '\'' +
               ", dbUser='" + dbUser + '\'' +
               ", mode='" + mode + '\'' +
               ", inputFile='" + inputFile + '\'' +
               ", onlyActive=" + onlyActive +
               ", minPrice=" + minPrice +
               '}';
    }
}
