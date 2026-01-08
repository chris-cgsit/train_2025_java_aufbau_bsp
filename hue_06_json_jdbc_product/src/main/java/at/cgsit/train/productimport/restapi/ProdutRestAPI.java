package at.cgsit.train.productimport.restapi;

import at.cgsit.train.productimport.model.Product;
import at.cgsit.train.productimport.service.ImportService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.List;

// in einem application server könnten wir hier dann einen einstiegspunkt machen
// für http REST requests die von uns für die GUI die benötigten services implementieren
// /products
// https://localhost:9090/products
public class ProdutRestAPI {

    // HTTTP GET METHODE im Application server
    public List<Product> getProducts() {
        // ImportService sericde = new ImportService();
        // return sericde.getProdudkts();
        return null;
    }

}
