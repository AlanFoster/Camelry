package me.alanfoster.camelus.blueprint.camel.dom.completion;

import java.util.List;

/**
 * Represents a basic CRUD service for a 'Person' Class.
 * This object exists purely for testing purposes
 */
public interface IPersonService {
    Person create();
    Person get(int id);
    List<Person> getAll();
    boolean update(Person person);
    boolean delete(Person person);
}
