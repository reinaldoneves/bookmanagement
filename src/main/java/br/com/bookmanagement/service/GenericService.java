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


}
