package at.cgsit.train.java.patterns.decorator;

public class TimeMeasureDecorator implements InfoSender{

    private final InfoSender wrapee;

    public TimeMeasureDecorator(InfoSender wrapee ) {
        this.wrapee = wrapee;
    }

    @Override
    public void send(String message) {
        System.out.printf("time measurs start\n" );

        long start = System.currentTimeMillis();

        wrapee.send(message);

        long end = System.currentTimeMillis();

        long durationsMs = end - start;
        System.out.printf("send() duration: [%s] ms\n", durationsMs);

    }
}
