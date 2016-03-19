package com.jsilgado.collections.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.jsilgado.collections.bean.CarBean;
import com.jsilgado.collections.bean.CarBrandBean;
import com.jsilgado.collections.bean.CarTrademarkBean;
import com.jsilgado.collections.exception.HelperException;
import com.jsilgado.collections.helper.CarBrandHelper;
import com.jsilgado.collections.helper.CarHelper;
import com.jsilgado.collections.helper.CarTrademarkHelper;
import com.jsilgado.collections.helper.FileHelper;

@ManagedBean(name = "carController")
@RequestScoped
@Controller
public class CarController implements ControllerTemplate<CarBean>, Serializable {

	private static final long serialVersionUID = -8366262763765794159L;

	@Autowired
	private CarHelper carHelper;

	@Autowired
	private CarTrademarkHelper carTrademarkHelper;

	@Autowired
	private CarBrandHelper carBrandHelper;

	@Autowired
	private FileHelper fileHelper;

	private List<String> lstUrlImage;

	@Override
	public CarBean initialize() {
		return new CarBean();
	}

	@Override
	public List<CarBean> getAll() {

		List<CarBean> lstCarBean = null;

		try {
			lstCarBean = this.carHelper.getAllCar();

			this.lstUrlImage = new ArrayList<>();

			this.lstUrlImage
					.add("http://localhost:8080/collections-mongodb-api/rest/json/getFile/56dc24affab9e418a8ca1b61");
			this.lstUrlImage
					.add("http://localhost:8080/collections-mongodb-api/rest/json/getFile/56dc24cefab9e418a8ca1b64");
			this.lstUrlImage
					.add("http://localhost:8080/collections-mongodb-api/rest/json/getFile/56dc8536fab9e421680b2b78");

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

		return lstCarBean;
	}

	public List<CarTrademarkBean> getAllCarTrademark() {

		List<CarTrademarkBean> lstCarTrademarkBeans = null;

		try {
			lstCarTrademarkBeans = this.carTrademarkHelper.getAllCarTrademark();

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

		return lstCarTrademarkBeans;
	}

	public List<CarBrandBean> getAllCarBrand() {

		List<CarBrandBean> lstCarBrandBeans = null;

		try {
			lstCarBrandBeans = this.carBrandHelper.getAllCarBrand();

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

		return lstCarBrandBeans;
	}

	@Override
	public CarBean getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(CarBean car) {

		try {
			if (this.validateCar(car)) {
				this.carHelper.insertCar(car);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha insertado correctamente"));
			}
		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

	}

	@Override
	public void update(CarBean t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

		try {
			this.carHelper.deleteCar(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha borrado correctamente"));

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

	}

	private boolean validateCar(CarBean car) {

		boolean bExit = true;

		if (StringUtils.isEmpty(car.getTrademark())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "La marca es obligatoria"));
			bExit = false;
		}

		if (StringUtils.isEmpty(car.getBrand())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Brand is required"));
			bExit = false;
		}

		return bExit;
	}

	public void detailCar(String id) {

		try {

			this.lstUrlImage.clear();
			CarBean carBean = this.carHelper.getCar(id);
			this.lstUrlImage.addAll(this.fileHelper.getUrlFile(carBean.getLstIdImage()));

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

	}

	public List<String> getLstUrlImage() {
		return this.lstUrlImage;
	}

	public void setLstUrlImage(List<String> lstUrlImage) {
		this.lstUrlImage = lstUrlImage;
	}

}
