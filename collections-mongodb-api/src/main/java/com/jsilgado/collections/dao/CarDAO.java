package com.jsilgado.collections.dao;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

//import com.jsilgado.collections.db.SpringMongoConfig;
import com.jsilgado.collections.domain.Car;
import com.jsilgado.collections.domain.CarTrademark;

@Repository
public class CarDAO{

	public Car insertCar(Car car) {

//		try {
//			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//			mongoOperation.save(car);
//
//
//		} catch (BeansException e) {
//			System.out.println("Error: " + e.getMessage());
//		}

		return car;
	}

	public Car findById(Car car) {

//		try {
//			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//			car = mongoOperation.findById(car.getId(), Car.class);
//
//
//		} catch (BeansException e) {
//			System.out.println("Error: " + e.getMessage());
//		}

		return car;
	}




	public Car deleteCar(Car car) {

//		try {
//			ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//			mongoOperation.remove(car);
//
//
//		} catch (BeansException e) {
//			System.out.println("Error: " + e.getMessage());
//		}

		return car;
	}

//	public List<Car> getCars () {
//
////		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
////	    MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
//
//		return mongoOperation.findAll(Car.class);
//	}
//
//	public List<CarTrademark> getCarTrademarks () {
//
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//		return mongoOperation.findAll(CarTrademark.class);
//	}


}
