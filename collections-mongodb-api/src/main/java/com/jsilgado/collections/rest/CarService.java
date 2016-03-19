package com.jsilgado.collections.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jsilgado.collections.domain.Car;
import com.jsilgado.collections.domain.CarBrand;
import com.jsilgado.collections.domain.CarTrademark;
import com.jsilgado.collections.dto.CarDTO;
import com.jsilgado.collections.dto.converters.CarConverter;

@Path("/json/car")
@Component
public class CarService {

	@Autowired
	private MongoTemplate mongotemplate;

	@GET
	@Path("/getAllCar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarDTO> getCarsAll() {

		return CarConverter.toDTO(this.mongotemplate.findAll(Car.class));
	}

	@POST
	@Path("/insertCar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCar(CarDTO carDTO) {

		CarTrademark carTrademark = this.mongotemplate
				.findOne(new Query(Criteria.where("id").is(carDTO.getCarTrademarkDTO().getId())), CarTrademark.class);
		CarBrand carBrand = this.mongotemplate
				.findOne(new Query(Criteria.where("id").is(carDTO.getCarBrandDTO().getId())), CarBrand.class);

		Car car = CarConverter.toEntity(carDTO);
		car.setBrand(carBrand);
		car.setTrademark(carTrademark);

		this.mongotemplate.save(car);

		return Response.ok().build();
	}

	@GET
	@Path("/getCar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gerCarTrademark(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.noContent().build();
		}

		Car car = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)), Car.class);

		return Response.ok().entity(car).build();
	}

	@GET
	@Path("/deleteCar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCarGET(@PathParam("id") String id) {
		// Car car = mongotemplate.findOne(new
		// Query(Criteria.where("id").is(id)), Car.class);
		// gridTemplate.delete(new
		// Query().addCriteria(Criteria.where("_id").is(carTrademark.getIdImage())));
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), Car.class);

		return Response.ok().build();
	}

}