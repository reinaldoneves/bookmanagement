package br.com.bookmanagement.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * A generic service to be used in all services of our applications
 * @param <Entity> the entity to be returned
 * @param <Repository> the repository of the entity to be used
 * @param <Parameter> the parameter to be used by the get methods
 * @author reinaldo_neves@hotmail.com
 * */
public interface GenericService<Entity, Repository extends MongoRepository, Parameter> {

    /**
     * Returns the entity based on the given id
     **/
    Entity getEntityById(Long id);

    /**
     * Returns the entity based on the given parameter
     **/
    Entity getEntityByParameter(Parameter parameter);

    /***
     * Retrieve the {@link List <Entity>} of all entities
     */
    List<Entity> getAllEntities();

    /***
     * Retrieve the {@link List <Entity>} of all entities bye the parameter
     */
    List<Entity> getAllEntitiesByParameter(Parameter parameter);

    /***
     * The creation of a new entity
     * @param newEntity the new entity to be created
     * @return Entity newEntity itself
     */
    Entity createEntity(Entity newEntity);

    /***
     * Update the character
     * @param entityUpdated the entity to be updated
     * @return Entity updated itsef
     */
    Entity updateEntity(Entity entityUpdated);

    /**
     * Delete a entity from the database based on his id
     * @param id the id of the entity to be deleted
     */
    void deleteEntity(Long id);

}
