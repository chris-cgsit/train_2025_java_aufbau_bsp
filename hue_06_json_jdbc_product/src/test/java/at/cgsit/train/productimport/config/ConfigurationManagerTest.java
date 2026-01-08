/*
 *  Copyright © 2026 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package at.cgsit.train.productimport.config;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {


  public void testit() {

    ConfigurationManager xon = new ConfigurationManager();
    AppConfig load = xon.load(null);

    AppConfig load1 = xon.load(null);
    load1.dbUrl();

    assertNotn

  }



}