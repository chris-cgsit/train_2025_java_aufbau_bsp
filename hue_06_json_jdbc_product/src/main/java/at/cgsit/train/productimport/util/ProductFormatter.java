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

package at.cgsit.train.productimport.util;

import at.cgsit.train.productimport.model.Product;

/**
 * Einfache Formatierung für die Konsolenausgabe von Product.
 */
public class ProductFormatter {


  public String format(Product p) {
    return "ID=" + p.getId() +
        " | name=" + p.getName() +
        " | price=" + p.getPrice() +
        " | active=" + p.isActive() +
        " | createdAt=" + p.getCreatedAt();
  }
}