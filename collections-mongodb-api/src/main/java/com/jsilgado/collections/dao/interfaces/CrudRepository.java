package com.jsilgado.collections.dao.interfaces;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {

	<S extends T> S save(S entity);

	T findOne(ID primaryKey, Class<T> objClass);

	Iterable<T> findAll(Class<T> objClass);

	Long count(Class<T> objClass);

	void delete(T entity);

	boolean exists(ID primaryKey);

}