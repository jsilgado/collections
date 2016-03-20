package com.jsilgado.collections.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import org.springframework.util.StringUtils;

import com.jsilgado.collections.domain.CarTrademark;

@Path("/json/carTrademark")
@Component
@Service
public class CarTrademarkService {

	@Autowired
	private MongoTemplate mongotemplate;

	@Autowired
	private GridFsTemplate gridTemplate;

	@GET
	@Path("/getAllCarTrademark")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CarTrademark> getCarsAll() {

		return this.mongotemplate.findAll(CarTrademark.class);

	}

	@POST
	@Path("/insertCarTrademark")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(CarTrademark carTrademark) {

		this.mongotemplate.save(carTrademark);

		return Response.ok().build();
	}

	@GET
	@Path("/getCarTrademark/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.noContent().build();
		}

		CarTrademark carTrademark = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)),
				CarTrademark.class);

		return Response.ok().entity(carTrademark).build();
	}

	@PUT
	@Path("/updateCarTrademark/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, CarTrademark carTrademark) {
		CarTrademark updatedCarTrademark = new CarTrademark();

		if (carTrademark.getName() == null) {
			return Response.status(400).entity("Please provide the carTrademark name !!").build();
		}

		updatedCarTrademark.setId(id);
		updatedCarTrademark.setName(carTrademark.getName());

		this.mongotemplate.insert(updatedCarTrademark);

		return Response.ok().entity(updatedCarTrademark).build();
	}

	@DELETE
	@Path("/deleteCarTrademark/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), CarTrademark.class);

		return Response.ok().build();
	}

	@GET
	@Path("/deleteCarTrademark/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteGET(@PathParam("id") String id) {
		CarTrademark carTrademark = this.mongotemplate.findOne(new Query(Criteria.where("id").is(id)),
				CarTrademark.class);
		this.gridTemplate.delete(new Query().addCriteria(Criteria.where("_id").is(carTrademark.getIdImage())));
		this.mongotemplate.remove(new Query(Criteria.where("id").is(id)), CarTrademark.class);

		return Response.ok().build();
	}

}