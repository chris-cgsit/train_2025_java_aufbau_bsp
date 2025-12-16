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

package at.cgsit.train.interf;

public interface TestInterface {

  Integer VAL = 0;

  final Integer VAL_FINAL = 0;

  static void doitStatic () {
     System.out.println("TestInterface doitStatic");
  }

  default void doitInstanceImpld()  {
    System.out.println("TestInterface doitInstanceImpl");
  }


  public void doitInstance () ;


}
