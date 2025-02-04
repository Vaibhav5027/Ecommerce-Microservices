package com.ecommerce.product.restimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.rest.ProductRest;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.utils.CafeConstants;
import com.ecommerce.product.utils.CafeUtils;
import com.ecommerce.product.wrapper.ProductWrapper;



@RestController
@CrossOrigin(value = "http://localhost:4200")
public class ProductRestImpl implements ProductRest {

	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<String> addProduct(Map<String, String> requestMap,String username) {
		try {
			
			return productService.addProduct(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
	}
	
	
//	    public ResponseEntity<Page<Product>> getProducts(
//	            @RequestParam(defaultValue = "0") int page,
//	            @RequestParam(defaultValue = "10") int size) {
//	    	
//	               
//	        return ResponseEntity.ok(products);
//	    }
	
	@Override
	public ResponseEntity<Page<Product>> getProducts(int page, int size) {
		 return  productService.getProducts(page, size);
		
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
		
			return productService.getAllProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestParam) {
		try {
			return productService.updateProduct(requestParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			return productService.deleteProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<String> updateProductStatus(Map<String, String> requestMap) {
		try {
			return productService.updateStatus(requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity  getProductByCategory(Integer id) {
		try {
			return productService.getProductByGategory(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
		
		try {
			return productService.getProductById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ProductWrapper(), HttpStatus.BAD_REQUEST);
	}
}
