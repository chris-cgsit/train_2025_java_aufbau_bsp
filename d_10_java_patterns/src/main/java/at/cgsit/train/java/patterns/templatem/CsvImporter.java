package at.cgsit.train.java.patterns.templatem;

public class CsvImporter extends DataImporter {

    @Override
    protected void readData() {
        System.out.println("Read CSV");
    }

    @Override
    protected void processData() {
        System.out.println("Process CSV data");
    }
}
