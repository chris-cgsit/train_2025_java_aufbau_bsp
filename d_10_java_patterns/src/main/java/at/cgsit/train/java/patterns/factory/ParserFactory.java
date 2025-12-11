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
