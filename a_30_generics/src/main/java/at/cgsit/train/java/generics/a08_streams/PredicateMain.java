package at.cgsit.train.java.generics.a08_streams;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateMain {

    static void main() {

        List<String> input = List.of(
                "chris", "cxxxris",
                "chalex", "max", "anna", "hugo");

        MyPredicate myPredicate = new MyPredicate();

        Predicate<String> myPredicate2 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("ch");
            }
        };

        Predicate<String> myPredicate3 = s -> {
            return s.startsWith("ch");
        };

//        Predicate<Integer> myPredicateWrong = s -> {
//            return s;
//        };

        List<Long> collectedResult = input.stream()
                .filter(myPredicate)  // 1. volle Implementierung mit einer Klasse
                .filter(myPredicate2) // 2. mit Anonymer Klasse
                .filter(myPredicate3) // 3. mit extra lambda oben annonyme klasse und annonyme Methode
                .filter(s -> s.startsWith("ch")) // 4. last not least direktes lambda presseion für das Predicate
                .map(s -> s.hashCode())
                .map(integer -> integer.longValue() + 42)
                .collect(Collectors.toList());

        collectedResult.forEach(System.out::println);

        // boolean result = myPredicate.test("cxxxris");
        // System.out.println("ergenbis des checks ist " + result);


    }

}
