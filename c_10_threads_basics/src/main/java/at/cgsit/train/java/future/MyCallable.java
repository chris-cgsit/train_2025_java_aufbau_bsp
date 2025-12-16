/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

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
