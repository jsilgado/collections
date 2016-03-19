package com.jsilgado.collections.helper;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.PropertyUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jsilgado.collections.bean.CarTrademarkBean;
import com.jsilgado.collections.dto.CarTrademarkDTO;
import com.jsilgado.collections.dto.converters.CarTrademarkConverter;
import com.jsilgado.collections.exception.HelperException;

@Service
public class CarTrademarkHelper implements Serializable {

	private static final long serialVersionUID = 5489228289588613850L;

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	@Autowired
	private FileHelper fileHelper;

	public List<CarTrademarkBean> getAllCarTrademark() throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("carTrademark").path("getAllCarTrademark");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

		CarTrademarkDTO[] list = response.readEntity(CarTrademarkDTO[].class);

		List<CarTrademarkBean> lstCarTrademarkBean = CarTrademarkConverter.toBean(Arrays.asList(list));

		for (CarTrademarkBean carTrademark : lstCarTrademarkBean) {

			if (!StringUtils.isEmpty(carTrademark.getIdImage())) {
				carTrademark.setUrlImage(this.restUrl + "/getFile/" + carTrademark.getIdImage());
			}
		}

		return lstCarTrademarkBean;

	}

	public void insertCarTrademark(CarTrademarkBean carTrademarkbean, InputStream stream) throws HelperException {

		String idImage = this.fileHelper.uploadFile(stream);

		carTrademarkbean.setIdImage(idImage);

		CarTrademarkDTO carTrademarkDTO = new CarTrademarkDTO();

		try {
			PropertyUtils.copyProperties(carTrademarkDTO, carTrademarkbean);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new HelperException(e.getMessage(), e.getMessage());
		}

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

		WebTarget webTarget = client.target(this.restUrl).path("carTrademark").path("insertCarTrademark");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(carTrademarkDTO, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

	public void deleteCarTrademark(String idCarTrademark) throws HelperException {

		Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
		WebTarget webTarget = client.target(this.restUrl).path("carTrademark/deleteCarTrademark/" + idCarTrademark);

		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			throw new HelperException(Integer.toString(response.getStatus()),
					"Failed : HTTP error code : " + response.getStatus());
		}

	}

}
