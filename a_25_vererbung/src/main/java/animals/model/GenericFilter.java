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

package animals.model;

// So würde das Interface als  generische Schnittstelle aussehen
// wenn wir es für Animal-Objekte brauchen, nehmen wir GenericFilter<Animal>
// wenn wir es für String-Objekte brauchen, nehmen wir GenericFilter<String>
public interface GenericFilter<T> {
    boolean isTrueFor(T obj);
}
