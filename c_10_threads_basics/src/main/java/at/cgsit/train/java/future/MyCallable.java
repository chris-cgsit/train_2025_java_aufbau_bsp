package at.cgsit.train.java.future;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<MyResult> {
    @Override
    public MyResult call() throws Exception {

        MyResult myResult = new MyResult();
        myResult.code = 1;
        myResult.value = "value";
        return myResult;
    }
}
