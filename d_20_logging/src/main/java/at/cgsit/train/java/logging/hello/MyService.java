package at.cgsit.train.java.logging.hello;

import java.util.logging.Logger;

public class MyService {

    private static final Logger LOG = Logger.getLogger(MyService.class.getName());

    public void doSomething() {

        LOG.fine("log message aus Service klasse");
    }

}
