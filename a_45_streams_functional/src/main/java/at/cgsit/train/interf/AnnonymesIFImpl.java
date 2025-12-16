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

import at.cgsit.train.java.objects.Buch;

public class AnnonymesIFImpl {

  public interface BuchFilter {
    boolean isTrueFor(Buch a);
  }


  static void main() {

    BuchFilter filter = new BuchFilter() {

      @Override
      public boolean isTrueFor(Buch a) {
        return false;
      }
    };


  }

}
