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
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
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

	@Autowired
	private GridFsTemplate gridTemplate;

	@GET
	@Path("/getAllCar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarDTO> getAll() {

		return CarConverter.toDTO(this.mongotemplate.findAll(Car.class));
	}

	@POST
	@Path("/insertCar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(CarDTO carDTO) {

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

	@POST
	@Path("/updateCar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(CarDTO carDTO) {

		Car car = this.mongotemplate.findOne(new Query(Criteria.where("id").is(carDTO.getId())), Car.class);

		if (carDTO.getCarTrademarkDTO() != null && carDTO.getCarTrademarkDTO().getId() != null) {
			CarTrademark carTrademark = this.mongotemplate.findOne(
					new Query(Criteria.where("id").is(carDTO.getCarTrademarkDTO().getId())), CarTrademark.class);
			car.setTrademark(carTrademark);
		} else {
			car.setTrademark(null);
		}

		if (carDTO.getCarBrandDTO() != null && carDTO.getCarBrandDTO().getId() != null) {
			CarBrand carBrand = this.mongotemplate
					.findOne(new Query(Criteria.where("id").is(carDTO.getCarBrandDTO().getId())), CarBrand.class);
			car.setBrand(carBrand);
		} else {
			car.setBrand(null);
		}

		car.setModel(carDTO.getModel());
		car.setYear(carDTO.getYear());

		this.mongotemplate.save(car);

		return Response.ok().build();
	}

	@GET
	@Path("/getCar/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gerById(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.noContent().build();
		}

		Car car = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)), Car.class);

		return Response.ok().entity(CarConverter.toDTO(car)).build();
	}

	@GET
	@Path("/deleteCar/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGET(@PathParam("id") String id) {
		Car car = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)), Car.class);

		for (String idImage : car.getLstIdImage()) {
			this.gridTemplate.delete(new Query().addCriteria(Criteria.where("_id").is(idImage)));
		}
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), Car.class);

		return Response.ok().build();
	}
	//
	// @GET
	// @Path("/deleteOrphanedImages")
	// public void deleteOrphanedImages() {
	//
	// List<String> lstCarImages = new ArrayList<>();
	//
	// retwet
	// FALTA LOS DE CARBRAN Y TRADEMARK
	// List<Car> lstCars = this.mongotemplate.findAll(Car.class);
	//
	// for (Car car : lstCars) {
	// if (car.getLstIdImage() != null) {
	// lstCarImages.addAll(car.getLstIdImage());
	// }
	//
	// }
	//
	// List<GridFSDBFile> find = this.gridTemplate.find(new Query());
	//
	// for (Object element : find) {
	// GridFSDBFile gridFSDBFile = (GridFSDBFile) element;
	// if (!lstCarImages.contains(gridFSDBFile.getId())) {
	// this.gridTemplate.delete(new
	// Query().addCriteria(Criteria.where("_id").is(gridFSDBFile.getId())));
	// }
	// }
	//
	// }

}