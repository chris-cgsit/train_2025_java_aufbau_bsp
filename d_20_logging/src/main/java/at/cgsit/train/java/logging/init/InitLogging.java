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

package at.cgsit.train.java.logging.init;

import com.sun.tools.javac.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class InitLogging {

  public static void init() {
    try (InputStream in =
             Main.class.getClassLoader()
                 .getResourceAsStream("logging.properties")) {

      LogManager.getLogManager().readConfiguration(in);

    } catch (Exception e) {
      System.err.println("Logging config failed");
    }

  }

}
