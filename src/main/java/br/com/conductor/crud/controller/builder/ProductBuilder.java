package br.com.conductor.crud.controller.builder;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import br.com.conductor.crud.model.Product;

public class ProductBuilder {

	@NotBlank(message = "logic is mandatory")
	public int logic;
	
	@NotBlank(message = "serial is mandatory")
    public String serial;
	
	@NotBlank(message = "model is mandatory")
    public String model;
	
	@NotBlank(message = "sam is mandatory")
    public int sam;
	
	@NotBlank(message = "ptid is mandatory")
    public String ptid;
	
	@NotBlank(message = "plat is mandatory")
    public int plat;
	
	@NotBlank(message = "Version is mandatory")
    public String version;
	
	@NotBlank(message = "Version is mandatory")
    public int mxr;
	
	@NotBlank(message = "mxf is mandatory")
    public int mxf;
	
	@NotBlank(message = "verfm is mandatory")
    public String verfm;
	
	public ProductBuilder(@NotBlank(message = "logic is mandatory") String logic, String serial, String model, String version) { 
    	this.logic = Integer.valueOf(StringUtils.isNotBlank(logic) ? logic : "0" );
    	this.serial = serial;
    	this.model = model;
    	this.version = version;
    }
	
	public ProductBuilder asSam(String sam) {
		this.sam = Integer.valueOf(sam);
		return this;
	}
	
	public ProductBuilder toPtid(String ptid) {
		this.ptid = ptid;
		return this;
	}

	public ProductBuilder toPlat(String plat) {
		this.plat = Integer.valueOf(plat);
		return this;
	}
	
	public ProductBuilder asMxr(String mxr) {
		this.mxr = Integer.valueOf(mxr);
		return this;
	}
	
	public ProductBuilder toMxf(String mxf) {
		this.mxf = Integer.valueOf(mxf);
		return this;
	}
	
	public ProductBuilder toVerfm(String verfm) {
		this.verfm = verfm;
		return this;
	}
	
	public Product builder() {
		Product form = new Product();
		form.setLogic(this.logic);
    	form.setSerial(this.serial);
    	form.setModel(this.model);
    	form.setSam(this.sam);
    	form.setPtid(this.ptid);
    	form.setPlat(this.plat);
    	form.setVersion(this.version);
    	form.setMxr(this.mxr);
    	form.setMxf(this.mxf);
    	form.setVERFM(this.verfm);
    	return form;
	}
	
}
