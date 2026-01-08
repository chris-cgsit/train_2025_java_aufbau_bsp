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

package at.cgsit.train.java.logging.file;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLoggingExample {

    private static final Logger LOG = Logger.getLogger(FileLoggingExample.class.getName());

    public static void main(String[] args) throws Exception {

        FileHandler fh = new FileHandler("logs/programaitsiches_app.log", true);
        fh.setFormatter(new SimpleFormatter());

        LOG.addHandler(fh);
        LOG.setUseParentHandlers(false);

        LOG.info("Written to file");
    }
}
