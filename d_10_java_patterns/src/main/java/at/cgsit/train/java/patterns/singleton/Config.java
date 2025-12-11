package at.cgsit.train.java.patterns.singleton;

public class Config {

    private static volatile Config INSTANCE;

    private Config() {
        // private Konstruktor
    }

    public static Config getInstance() {
        if (INSTANCE == null) {
            synchronized (Config.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Config();
                }
            }
        }
        return INSTANCE;
    }
}
