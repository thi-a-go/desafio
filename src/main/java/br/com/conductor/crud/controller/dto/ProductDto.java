package br.com.conductor.crud.controller.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import br.com.conductor.crud.model.Product;

public class ProductDto implements Serializable {
	
	private static final long serialVersionUID = -3505942309483960406L;

	public int logic;
    public String serial;
    public String model;
    public int sam;
    public String ptid;
    public int plat;
    public String version;
    public int mxr;
    public int mxf;
    public String vERFM;
    
    public ProductDto (Product product){
    	this.logic = product.getLogic();
    	this.serial = product.getSerial();
    	this.model = product.getModel();
    	this.sam = product.getSam();
    	this.ptid = product.getPtid();
    	this.plat = product.getPlat();
    	this.version = product.getVersion();
    	this.mxr = product.getMxr();
    	this.mxf = product.getMxf();
    	this.vERFM  = product.getVERFM();
    }
    
	public int getLogic() {
		return logic;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getSam() {
		return sam;
	}
	
	public String getPtid() {
		return ptid;
	}
	
	public int getPlat() {
		return plat;
	}
	
	public String getVersion() {
		return version;
	}
	
	public int getMxr() {
		return mxr;
	}
	
	public int getMxf() {
		return mxf;
	}
	
	public String getvERFM() {
		return vERFM;
	}

	public static List<ProductDto> convert(List<Product> asProducts) {
		return asProducts.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	     
}
