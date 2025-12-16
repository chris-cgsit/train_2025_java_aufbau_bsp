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

package at.cgsit.train.java.patterns.factory;

public class ParserFactory {

    public static Parser create(String type) {
        return switch (type.toLowerCase()) {
            case "json" -> new JsonParser();
            case "xml" -> new XmlParser();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
