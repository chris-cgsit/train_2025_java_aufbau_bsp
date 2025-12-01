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