package at.cgsit.train.java.records;

record Email(String value) {
    public Email {
        if (!value.contains("@")) throw new IllegalArgumentException("Invalid email");
    }
}
