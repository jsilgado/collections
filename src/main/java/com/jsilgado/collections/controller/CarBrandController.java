package com.jsilgado.collections.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.jsilgado.collections.bean.CarBrandBean;
import com.jsilgado.collections.bean.ImageBean;
import com.jsilgado.collections.exception.HelperException;
import com.jsilgado.collections.helper.CarBrandHelper;

@ManagedBean(name = "carBrandController")
@RequestScoped
@Controller
public class CarBrandController implements ControllerTemplate<CarBrandBean>, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8366262763765794159L;

	@Autowired
	private CarBrandHelper carBrandHelper;

	private UploadedFile file;

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	@Override
	public CarBrandBean initialize() {
		return new CarBrandBean();
	}

	@Override
	public List<CarBrandBean> getAll() {

		List<CarBrandBean> carBrands = null;

		try {
			carBrands = this.carBrandHelper.getAllCarBrand();

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

		return carBrands;
	}

	@Override
	public CarBrandBean getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(CarBrandBean carBrandBean) {

		try {
			if (this.validateCarBrand(carBrandBean)) {

				ImageBean imageBean = new ImageBean();
				imageBean.setFile(this.file);
				carBrandBean.setImageBean(imageBean);

				this.carBrandHelper.insertCarBrand(carBrandBean);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha insertado correctamente"));
			}
		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}
	}

	@Override
	public void update(CarBrandBean t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {

		try {
			this.carBrandHelper.deleteCarBrand(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha borrado correctamente"));

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

	}

	private boolean validateCarBrand(CarBrandBean carBrandBean) {

		boolean bExit = true;

		if (StringUtils.isEmpty(carBrandBean.getName())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "EL nombre es obligatorio"));
			bExit = false;
		}

		if (StringUtils.isEmpty(this.file)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "La imagen es obligatorio"));
			bExit = false;
		}

		return bExit;
	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);

		this.setFile(event.getFile());
	}

	public UploadedFile getFile() {
		return this.file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
