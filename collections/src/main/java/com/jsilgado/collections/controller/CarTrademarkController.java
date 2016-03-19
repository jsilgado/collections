package com.jsilgado.collections.controller;

import java.io.IOException;
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

import com.jsilgado.collections.bean.CarTrademarkBean;
import com.jsilgado.collections.exception.HelperException;
import com.jsilgado.collections.helper.CarTrademarkHelper;

@ManagedBean(name = "carTrademarkController")
@RequestScoped
@Controller
public class CarTrademarkController implements ControllerTemplate<CarTrademarkBean>, Serializable {

	private static final long serialVersionUID = -8366262763765794159L;

	@Autowired
	private CarTrademarkHelper carTrademarkHelper;

	private UploadedFile file;

	@Value("#{restProperties['rest.urlRest']}")
	private String restUrl;

	@Override
	public CarTrademarkBean initialize() {
		return new CarTrademarkBean();
	}

	@Override
	public List<CarTrademarkBean> getAll() {

		List<CarTrademarkBean> carTrademarks = null;
		try {
			carTrademarks = this.carTrademarkHelper.getAllCarTrademark();

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}

		return carTrademarks;
	}

	@Override
	public CarTrademarkBean getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(CarTrademarkBean carTrademarkBean) {

		try {
			if (this.validateCarTrademark(carTrademarkBean)) {
				this.carTrademarkHelper.insertCarTrademark(carTrademarkBean, this.file.getInputstream());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha insertado correctamente"));
			}
		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

	}

	@Override
	public void update(CarTrademarkBean t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		try {
			this.carTrademarkHelper.deleteCarTrademark(id);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Se ha borrado correctamente"));

		} catch (HelperException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getCodeError(), e.getDescription()));
		}
	}

	private boolean validateCarTrademark(CarTrademarkBean carTrademarkBean) {

		boolean bExit = true;

		if (StringUtils.isEmpty(carTrademarkBean.getName())) {
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
