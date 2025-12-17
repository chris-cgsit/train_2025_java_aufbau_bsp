package at.cgsit.train.java.generics.work;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestWithSetMain {

    static void main() {

        Set<String> input = new HashSet<>(Arrays.asList(
                "chris",
                "cxxxris",
                "chalex",
                "max",
                "anna",
                "hugo"
        ));

        // Wenn remove(Object) häufig ist → Set objekt zugriff via hash
        // Wenn Index wichtig ist → List

        // zugriff extrem schnell da mit hash code die position gefunden wird
        // wo das element liegt:
        // O(1) durchschnittlich also 1 zugriff
        boolean removed = input.remove("chris");

        System.out.println("Removed: " + removed);
        input.forEach(System.out::println);

    }

}
