/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

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
