package at.cgsit.train.java.generics.a05_interfaces;

/**
 * Interface with Type Paramter
 * @param <ENTITY_TYPE>
 */
public interface Repository<ENTITY_TYPE> {

    ENTITY_TYPE findById(long id);

    void save(ENTITY_TYPE entity);
}



