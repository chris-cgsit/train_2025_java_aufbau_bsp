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

public class FilterHelper {
    // diese Methode passt (außer beim Namen) zur Signatur von
    // boolean AnimalFilter.isTrueFor(Animal a)
    // daher kann die Methode als Method Reference für eine AnimalFilter-Implementierung
    // eingesetzt werden (wir brauchen für den Aufruf aber ein Objekt)
    public boolean isVegetarian(Animal a){
        return a.isHerbivore();
    }

    public boolean isLightweight(Animal a){
        return a.getWeight() < 500;
    }
}
