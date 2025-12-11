package at.cgsit.train.java.patterns.factory;

public class MainParser {

  static void main() {
    Parser parser = ParserFactory.create("json");
    parser.parse("{ ... }");

    Parser parser2 = ParserFactory.create("xml");
    parser2.parse("<tag></tag>");
  }

}
