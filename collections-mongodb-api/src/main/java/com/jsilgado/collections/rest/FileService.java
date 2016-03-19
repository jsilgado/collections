package com.jsilgado.collections.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

@Path("/json")
@Component
@Service
public class FileService {

	@Autowired
	private GridFsTemplate gridTemplate;

	@POST
	@Path("/uploadFile")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {

		GridFSFile file = this.gridTemplate.store(fileInputStream, fileMetaData);

		return Response.ok().entity(file.getId().toString()).build();

	}

	@GET
	@Path("/getFile/{id}")
	public Response getFile(@PathParam("id") String id) {

		GridFSDBFile file = this.gridTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));

		if (file == null) {
			return Response.noContent().build();
		}

		InputStream inputStream = file.getInputStream();

		StreamingOutput fileStream = new StreamingOutput() {

			@Override
			public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
				try {
					byte[] buffer = new byte[256];
					int bytesRead = 0;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						output.write(buffer, 0, bytesRead);
					}

					output.flush();
				} catch (Exception e) {
					throw new WebApplicationException("File Not Found !!");
				}
			}
		};

		return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM).build();
	}

	@GET
	@Path("/deleteAll")
	public Response deleteAll() {
		this.gridTemplate.delete(new Query());

		return Response.ok().build();

	}

	@GET
	@Path("/getAll")
	public Response getAll() {
		List<GridFSDBFile> find = this.gridTemplate.find(new Query());

		for (Object element : find) {
			GridFSDBFile gridFSDBFile = (GridFSDBFile) element;
			System.out.println(gridFSDBFile.getId());

		}

		return Response.ok().build();

	}

}