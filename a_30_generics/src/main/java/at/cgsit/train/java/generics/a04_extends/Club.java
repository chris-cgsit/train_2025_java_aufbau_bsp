package at.cgsit.train.java.generics.a04_extends;

public class Club<T extends Person> {

    private final T member;

    public Club(T member) {
        this.member = member;
    }

    public void printName() {
        System.out.println(member.getName());
    }
}
