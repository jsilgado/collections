package com.jsilgado.collections.helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jsilgado.collections.bean.CarBean;
import com.jsilgado.collections.bean.ImageBean;
import com.jsilgado.collections.dto.CarDTO;
import com.jsilgado.collections.dto.converters.CarConverter;
import com.jsilgado.collections.exception.HelperException;

@Service
public class CarHelper implements Serializable {

	private static final long serialVersionUID = 5489758289588613850L;

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	@Autowired
	private FileHelper fileHelper;

	public List<CarBean> getAllCar() throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

		WebTarget webTarget = client.target(this.restUrl).path("car/getAllCar");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

		CarDTO[] list = response.readEntity(CarDTO[].class);

		List<CarBean> lstCarBean = CarConverter.toBean(Arrays.asList(list));

		for (CarBean carBean : lstCarBean) {

			for (ImageBean imageBean : carBean.getLstImagenBean()) {
				imageBean.setUrl(this.fileHelper.getUrlFile(imageBean.getId()));
			}
		}

		return lstCarBean;

	}

	public void insertCar(CarBean carbean) throws HelperException {

		try {
			for (ImageBean imageBean : carbean.getLstImagenBean()) {

				String idImage = this.fileHelper.uploadFile(imageBean.getFile().getInputstream());
				imageBean.setId(idImage);
				imageBean.setUrl(this.fileHelper.getUrlFile(idImage));
			}

			CarDTO carDTO = CarConverter.toDTO(carbean);

			Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

			WebTarget webTarget = client.target(this.restUrl).path("car").path("insertCar");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(carDTO, MediaType.APPLICATION_JSON));

			if (response.getStatus() != 200) {
				throw new HelperException(Integer.toString(response.getStatus()),
						"Failed : HTTP error code : " + response.getStatus());
			}
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getMessage()));
		}
	}

	public void updateCar(CarBean carbean) throws HelperException {

		CarDTO carDTO = CarConverter.toDTO(carbean);

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

		WebTarget webTarget = client.target(this.restUrl).path("car").path("updateCar");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(carDTO, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

	public void deleteCar(String idCar) throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("car/deleteCar/" + idCar);

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

	public CarBean getCar(String idCar) throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("car/getCar/" + idCar);

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

		CarDTO carDTO = response.readEntity(CarDTO.class);

		CarBean carBean = CarConverter.toBean(carDTO);

		for (ImageBean imageBean : carBean.getLstImagenBean()) {
			imageBean.setUrl(this.fileHelper.getUrlFile(imageBean.getId()));
		}

		return carBean;
	}

}
