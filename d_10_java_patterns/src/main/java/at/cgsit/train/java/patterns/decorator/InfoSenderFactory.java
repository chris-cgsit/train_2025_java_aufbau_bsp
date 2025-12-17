package at.cgsit.train.java.patterns.decorator;

public class InfoSenderFactory {

    public static InfoSender factoryMethdforInfoSender() {

        InfoSender notifierwithSmsCore =
                new TimeMeasureDecorator(
                        new NotifierDecorator( new SMSNotifier() ) );

        return notifierwithSmsCore;
    }

}
