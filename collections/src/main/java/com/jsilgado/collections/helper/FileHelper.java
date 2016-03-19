package com.jsilgado.collections.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jsilgado.collections.exception.HelperException;

@Service
public class FileHelper {

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	public String uploadFile(InputStream stream) throws HelperException {
		final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

		BodyPart bodyPart = new StreamDataBodyPart("file", stream);

		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(bodyPart);

		final WebTarget webTarget = client.target(this.restUrl).path("uploadFile");
		final Response response = webTarget.request().post(Entity.entity(multipart, multipart.getMediaType()));

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

		String idFile = response.readEntity(String.class);

		try {
			formDataMultiPart.close();
			multipart.close();
		} catch (IOException e) {
			throw new HelperException(e.getMessage(), e.getMessage());
		}

		return idFile;

	}

	public String getUrlFile(String idImage) throws HelperException {

		return this.restUrl + "/getFile/" + idImage;

	}

	public List<String> getUrlFile(List<String> lstIdImage) throws HelperException {

		List<String> lstUrlImage = new ArrayList<>();

		for (String string : lstIdImage) {
			String idImage = string;
			lstUrlImage.add(this.getUrlFile(idImage));
		}

		return lstUrlImage;

	}

}
