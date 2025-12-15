package at.cgsit.train.java.generics.a04_extends;

public class Player extends Person {

    public Player(String name) {
        super(name);
    }

    public void play() {
        System.out.println(getName() + " spielt");
    }
}
