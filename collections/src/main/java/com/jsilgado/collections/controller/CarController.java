package com.jsilgado.collections.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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

	private List<UploadedFile> lstFiles;

	@Override
	public CarBean initialize() {

		this.lstFiles = new ArrayList<>();
		this.lstUrlImage = new ArrayList<>();
		return new CarBean();
	}

	@Override
	public List<CarBean> getAll() {

		List<CarBean> lstCarBean = null;

		try {
			lstCarBean = this.carHelper.getAllCar();

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

				List<InputStream> lstStream = new ArrayList<>();

				for (Object element : this.lstFiles) {
					UploadedFile uploadedFile = (UploadedFile) element;
					lstStream.add(uploadedFile.getInputstream());
				}

				this.carHelper.insertCar(car, lstStream);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha insertado correctamente"));
			}
		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, null, e.getMessage()));
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

			Map<String, Object> options = new HashMap<String, Object>();
			options.put("resizable", false);
			options.put("draggable", false);
			options.put("modal", true);
			RequestContext.getCurrentInstance().openDialog("carDialog", options, null);

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		this.getLstFiles().add(event.getFile());

	}

	public List<String> getLstUrlImage() {
		return this.lstUrlImage;
	}

	public void setLstUrlImage(List<String> lstUrlImage) {
		this.lstUrlImage = lstUrlImage;
	}

	public List<UploadedFile> getLstFiles() {
		return this.lstFiles;
	}

	public void setLstFiles(List<UploadedFile> lstFiles) {
		this.lstFiles = lstFiles;
	}
}
