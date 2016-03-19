package com.jsilgado.collections.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.springframework.stereotype.Service;

import com.jsilgado.collections.domain.CarBrand;
import com.jsilgado.collections.domain.CarTrademark;
import com.jsilgado.collections.dto.CarBrandDTO;
import com.jsilgado.collections.dto.converters.CarBrandConverter;

@Path("/json/carBrand")
@Component
@Service
public class CarBrandService {

	@Autowired
	private MongoTemplate mongotemplate;

	@Autowired
	private GridFsTemplate gridTemplate;

	@GET
	@Path("/getAllCarBrand")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarBrandDTO> getCarsAll() {

		return CarBrandConverter.toDTO(this.mongotemplate.findAll(CarBrand.class));

	}

	@POST
	@Path("/insertCarBrand")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCarBrand(CarBrandDTO carBrandDTO) {

		this.mongotemplate.save(CarBrandConverter.toEntity(carBrandDTO));

		return Response.ok().build();
	}

	@DELETE
	@Path("/deleteCarBrand/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCarTrademark(@PathParam("id") String id) {
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), CarTrademark.class);

		return Response.ok().build();
	}

	@GET
	@Path("/deleteCarBrand/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteCarBrandkGET(@PathParam("id") String id) {
		CarBrand carBrand = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)), CarBrand.class);
		this.gridTemplate.delete(new Query().addCriteria(Criteria.where("_id").is(carBrand.getIdImage())));
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), CarBrand.class);

		return Response.ok().build();
	}

}