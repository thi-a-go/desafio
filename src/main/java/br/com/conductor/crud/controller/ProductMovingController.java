package br.com.conductor.crud.controller;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.conductor.crud.controller.builder.ProductBuilder;
import br.com.conductor.crud.controller.dto.ProductDto;
import br.com.conductor.crud.helper.AppHelper;
import br.com.conductor.crud.model.Product;
import br.com.conductor.crud.repository.ProductMovinngRepository;

@RestController
@RequestMapping("/{getVersionProject()}/products")
public class ProductMovingController {

	@Autowired
	private ProductMovinngRepository productMovinngRepository; 
	
	@GetMapping
	public List<ProductDto> list(String logic) {

		List<Product> products;
		
		if(isNotBlank(logic)) {
			products = productMovinngRepository.findByLogic(Integer.valueOf(logic));
		} else {
			products = productMovinngRepository.findAll();
		}
		
		return ProductDto.convert(products);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_HTML_VALUE )
	public ResponseEntity<ProductDto> add(@Validated @RequestBody String body, UriComponentsBuilder uriBuilder) {
		Product product = preparedProduct(body, null);
				
		productMovinngRepository.save(product);
				
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getLogic()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
				
	}
	
	@DeleteMapping(value="/{logic}")
	public ResponseEntity<String> delete(@Valid String logic) {
		return ResponseEntity.badRequest().body("We do not carry out this type of operation, please contact ADM!");				
	}
	
	@Transactional
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.TEXT_HTML_VALUE, value="/{logic}" )
	public ResponseEntity<ProductDto> update(@PathVariable @Valid String logic, @RequestBody @Valid String form){
		
		Product product = productMovinngRepository.getById(Integer.valueOf(logic));
		product = preparedProduct(form, logic);
		
		return ResponseEntity.ok(new ProductDto(product));
	}

	private Product preparedProduct(String body, String logic) {
		List<String> prepadedPoduct = AppHelper.convertStringToArray(body);
		
		ProductBuilder pdtBuilder;
		
		if (StringUtils.isNotBlank(logic)) {
			pdtBuilder = new ProductBuilder(logic, prepadedPoduct.get(1), prepadedPoduct.get(2),prepadedPoduct.get(6));
		} else {
			pdtBuilder = new ProductBuilder(prepadedPoduct.get(0), prepadedPoduct.get(1), prepadedPoduct.get(2),prepadedPoduct.get(6));
		}
		
		pdtBuilder.asSam(prepadedPoduct.get(3)).toPtid(prepadedPoduct.get(4))
				  .toPlat(prepadedPoduct.get(5)).asMxr(prepadedPoduct.get(7)).toMxf(prepadedPoduct.get(8))
				  .toVerfm(prepadedPoduct.get(9));
		
		return pdtBuilder.builder();
	}
	
}
