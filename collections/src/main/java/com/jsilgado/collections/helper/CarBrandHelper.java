package com.jsilgado.collections.helper;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.util.StringUtils;

import com.jsilgado.collections.bean.CarBrandBean;
import com.jsilgado.collections.dto.CarBrandDTO;
import com.jsilgado.collections.dto.converters.CarBrandConverter;
import com.jsilgado.collections.exception.HelperException;

@Service
public class CarBrandHelper implements Serializable {

	private static final long serialVersionUID = 5489758289488613850L;

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	@Autowired
	private FileHelper fileHelper;

	public List<CarBrandBean> getAllCarBrand() throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("carBrand").path("getAllCarBrand");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

		CarBrandDTO[] list = response.readEntity(CarBrandDTO[].class);

		List<CarBrandBean> lstCarBrandBean = CarBrandConverter.toBean(Arrays.asList(list));

		for (CarBrandBean carBrandBean : lstCarBrandBean) {

			if (!StringUtils.isEmpty(carBrandBean.getIdImage())) {
				carBrandBean.setUrlImage(this.restUrl + "/getFile/" + carBrandBean.getIdImage());
			}
		}

		return lstCarBrandBean;

	}

	public void insertCarBrand(CarBrandBean carBrandBean, InputStream stream) throws HelperException {

		String idImage = this.fileHelper.uploadFile(stream);

		carBrandBean.setIdImage(idImage);

		CarBrandDTO carBrandDTO = CarBrandConverter.toDTO(carBrandBean);

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

		WebTarget webTarget = client.target(this.restUrl).path("carBrand").path("insertCarBrand");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(carBrandDTO, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

	public void deleteCarBrand(String id) throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("carBrand/deleteCarBrand/" + id);

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

}
