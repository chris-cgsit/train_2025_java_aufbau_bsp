package at.cgsit.train.java.patterns.templatem;

public abstract class DataImporter {

    public final void importData() {
        readData();
        processData();
        saveData();
    }

    protected abstract void readData();
    protected abstract void processData();

    protected void saveData() {
        System.out.println("Save to database");
    }
}
