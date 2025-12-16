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

package at.cgsit.train.java.generics.a05_interfaces;

/**
 * Interface with Type Paramter
 * @param <ENTITY_TYPE>
 */
public interface Repository<ENTITY_TYPE> {

    ENTITY_TYPE findById(long id);

    void save(ENTITY_TYPE entity);
}



