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

package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;

public class HeavyAnimalFilter implements AnimalFilter {

    // Schwellwert (ab wann ein Tier als schwer gilt)
    private int fromWeight;

    public HeavyAnimalFilter(int fromWeight) {
        this.fromWeight = fromWeight;
    }


    /**
     * Testet ob das Tier im Ergebnis  enthalten sein soll oder nicht
     *
     * @param a das Tier
     * @return true wenn es enthalten sein soll
     */
    @Override
    public boolean isTrueFor(Animal a) {
        return a.getWeight() >= fromWeight;
    }
}
