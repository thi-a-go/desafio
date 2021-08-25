package br.com.conductor.crud.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;

import br.com.conductor.crud.controller.builder.ProductBuilder;
import br.com.conductor.crud.controller.dto.ProductDto;
import br.com.conductor.crud.helper.AppHelper;
import br.com.conductor.crud.model.Product;
import br.com.conductor.crud.repository.ProductMovinngRepository;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class ProductMovingControllerTest {

	@InjectMocks
	private ProductMovingController controller;
	
	@Mock
	private ProductMovinngRepository productMovinngRepository; 
	
	@Mock
	private UriComponentsBuilder uriBuilder;

	private final static String BODY_DEDAULT = "44332211;123;PWWIN;0;F04A2E4088B;4;8.00b3;0;16777216;PWWIN";
	
	private final static String LOGIC_DEFAULT = "44332211";
	
	@Test
	void testAdd() throws Exception {
		
		Map<String, Object> retorno = new HashMap<>();
		retorno.put("response", "<201,[X-Powered-By:\"Servlet/3.1\", Content-Language:\"pt-BR\", Content-Length:\"0\", Date:\"Sun, 18 Apr 2021 14:55:12 GMT\"]>");
		
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme("http").host("127.0.0.1").path("/products/").queryParam("1").fragment("1").build();
		
		ResponseEntity<?> response = controller.add(BODY_DEDAULT, builder);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void testDelete() throws Exception {
		ResponseEntity<?> response = controller.delete("44332211");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void testUpdate() throws Exception {
		
		Map<String, Object> retorno = new HashMap<>();
		retorno.put("response", "<200,[X-Powered-By:\"Servlet/3.1\", Content-Language:\"pt-BR\", Content-Length:\"0\", Date:\"Sun, 18 Apr 2021 14:55:12 GMT\"]>");
		
		ResponseEntity<?> response = controller.update(LOGIC_DEFAULT, BODY_DEDAULT);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testListAll() throws Exception {
		
		when(productMovinngRepository.findAll()).thenReturn(preparedProduct("0"));
					
		List<ProductDto> products = controller.list(null);
		assertTrue(products.size() > 0);
		//assertEquals(HttpStatus.OK, products);
	}
	
	@Test
	void testListOneResult() throws Exception {
		
		when(productMovinngRepository.findByLogic(Integer.valueOf(LOGIC_DEFAULT))).thenReturn(preparedProduct(LOGIC_DEFAULT));
					
		List<ProductDto> products = controller.list(LOGIC_DEFAULT);
		assertTrue(products.size() > 0);
		//assertEquals(HttpStatus.OK, products);
	}
	
	private List<Product> preparedProduct(String logic) {
		List<String> prepadedPoduct = AppHelper.convertStringToArray(BODY_DEDAULT);
		
		ProductBuilder pdtBuilder;
		
		if (StringUtils.isNotBlank(logic)) {
			pdtBuilder = new ProductBuilder(logic, prepadedPoduct.get(1), prepadedPoduct.get(2),prepadedPoduct.get(6));
		} else {
			pdtBuilder = new ProductBuilder(prepadedPoduct.get(0), prepadedPoduct.get(1), prepadedPoduct.get(2),prepadedPoduct.get(6));
		}
		
		pdtBuilder.asSam(prepadedPoduct.get(3)).toPtid(prepadedPoduct.get(4))
				  .toPlat(prepadedPoduct.get(5)).asMxr(prepadedPoduct.get(7)).toMxf(prepadedPoduct.get(8))
				  .toVerfm(prepadedPoduct.get(9));
				
		List<Product> ps = new ArrayList<Product>();
		ps.add(pdtBuilder.builder());
		
		return ps;
	}
}
