package at.cgsit.train.java.generics.a08_streams;

import java.util.function.Predicate;

public class MyPredicate implements Predicate<String> {

    @Override
    public boolean test(String s) {
        if (s == null) return false;
        return s.toLowerCase().startsWith("ch");
    }

}
