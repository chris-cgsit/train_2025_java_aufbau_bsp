package at.cgsit.train.java.generics.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMain {

    static void main() {

        //List<String> input = Arrays.asList(
        List<String> input = new ArrayList<>(Arrays.asList(
                "chris",
                "cxxxris",
                "chalex",
                "max",
                "anna",
                "hugo"));

        // List<String> input222 = new ArrayList<>(10);
        // input.add("Chris");
        // input.add("max");

        int index = input.indexOf("chris"); // O(n) – einmalig

        // das ist super performant da direkter sprung auf den index 1
        input.remove(0);
        // performance mässig gefährlich weil das erste Element gesucht wird das hier zutrifft, kein hash code sprung
        boolean removed = input.remove("chris");
        input.forEach(System.out::println);
    }

}
