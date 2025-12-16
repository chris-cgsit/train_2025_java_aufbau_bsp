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

package at.cgsit.train.java.wrapper;

import java.io.Serializable;

public class MessageWrapper<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String type;
    private final T payload;

    public MessageWrapper(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "MessageWrapper{type='" + type + "', payload=" + payload + "}";
    }
}
