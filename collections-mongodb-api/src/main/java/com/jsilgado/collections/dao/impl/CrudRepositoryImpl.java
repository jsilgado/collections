package com.jsilgado.collections.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.jsilgado.collections.dao.interfaces.CrudRepository;
//import com.jsilgado.collections.db.SpringMongoConfig;
import com.mongodb.DBCollection;

@Repository
public class CrudRepositoryImpl<T> implements CrudRepository<T, Serializable>{

	@Override
	public <S extends T> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne(Serializable primaryKey, Class<T> objClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<T> findAll(Class<T> objClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(Class<T> objClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(Serializable primaryKey) {
		// TODO Auto-generated method stub
		return false;
	}


//	@Override
//	public <S extends T> S save(S entity) {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		mongoOperation.save(entity);
//
//		return entity;
//	}
//
//
//	public T findOne(Serializable id, T obj) {
//
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		obj = (T) mongoOperation.findById(id, obj.getClass());
//
//		return obj;
//	}
//
//
//	@Override
//	public T findOne(Serializable primaryKey, Class<T> objClass) {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		T obj = (T) mongoOperation.findById(primaryKey, objClass);
//
//		return obj;
//	}
//
//
//	@Override
//	public Iterable<T> findAll(Class<T> objClass) {
//
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		Iterable<T> entities = (List<T>) mongoOperation.findAll(objClass);
//
//		return entities;
//	}
//
//
//	@Override
//	public Long count(Class<T> objClass) {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		DBCollection collection = mongoOperation.getCollection(objClass.getName().toLowerCase());
//
//		return collection.count();
//	}
//
//
//	@Override
//	public boolean exists(Serializable primaryKey) {
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		return false;
//	}
//
//
//	@Override
//	public void delete(T entity) {
//
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		mongoOperation.remove(entity);
//
//	}
//








}
