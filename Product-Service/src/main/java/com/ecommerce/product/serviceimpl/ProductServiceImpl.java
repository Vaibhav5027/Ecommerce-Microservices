package com.ecommerce.product.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.product.model.Category;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repo.CategoryRepo;
import com.ecommerce.product.repo.ProductRepository;
import com.ecommerce.product.service.ProductService;
import com.ecommerce.product.utils.CafeConstants;
import com.ecommerce.product.wrapper.ProductWrapper;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository prodRepo;
	@Autowired
	CategoryRepo cateRepo;

//	@Autowired
//	JwtAuthFilter jwtFilter;

	@Override
	public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
		try {
//			if (jwtFilter.isAdmin()) {
			if (validateProductMap(requestMap, false)) {
				Product product = getProductFromMap(requestMap, false);
				prodRepo.save(product);
				return new ResponseEntity<String>("{\"message\" : \"product saved succefully\"}"  , HttpStatus.OK);
			}

			return new ResponseEntity<String>("Invalid Product", HttpStatus.BAD_REQUEST);
//			}

//			return new ResponseEntity<String>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
			       List<Product> all = prodRepo.findAll();
			       List<ProductWrapper> list = all.stream().map(product->new ProductWrapper(product.getProductId(), product.getName(),product.getDescription(),product.getStatus(),product.getPrice(),product.getCategory().getCategoryName(),product.getImageUrl())).toList();
			
			return new ResponseEntity<List<ProductWrapper>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	

	private Product getProductFromMap(Map<String, String> requestMap, boolean isUpdate) {
	
	Category category=cateRepo.findById(Integer.parseInt(requestMap.get("categoryId"))).get();
   System.out.println(category);
		Product product = new Product();
		if (isUpdate) {
			Product product2 = prodRepo.findById(Integer.parseInt(requestMap.get("id"))).get();
			product.setProductId(Integer.parseInt(requestMap.get("id")));
			product.setStatus(product2.getStatus());
		} else {
			product.setStatus("true");
		}
		product.setCategory(category);
		product.setName(requestMap.get("name"));
		product.setDescription(requestMap.get("description"));
		product.setPrice(Integer.parseInt(requestMap.get("price")));

		return product;
	}

	private boolean validateProductMap(Map<String, String> requestMap, boolean validate) {
		if (requestMap.containsKey("name")) {
			if (requestMap.containsKey("id") && validate) {
				return true;
			} else if (!validate) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestParam) {
		try {
//			if (jwtFilter.isAdmin()) {
			if (validateProductMap(requestParam, true)) {
				Optional<Product> optional = prodRepo.findById(Integer.parseInt(requestParam.get("id")));
				if (!optional.isEmpty()) {
					Product productFromMap = getProductFromMap(requestParam, true);
					prodRepo.save(productFromMap);
					return new ResponseEntity<String>("Product Updated Succesfully", HttpStatus.OK);
				}
				return new ResponseEntity<String>("Invalid Product", HttpStatus.BAD_REQUEST);
			}
//			}
			return new ResponseEntity<String>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
//		if (jwtFilter.isAdmin()) {
		Optional<Product> product = prodRepo.findById(id);

		if (!product.isEmpty()) {
			prodRepo.deleteById(id);
			return new ResponseEntity<String>(" Product Deleted Succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>(" Product not found", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
//		if (jwtFilter.isAdmin()) {
		Optional<Product> product = prodRepo.findById(Integer.parseInt(requestMap.get("id")));

		if (!product.isEmpty()) {
//			prodRepo.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
			return new ResponseEntity<String>(" Product  Status Updated Succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>(" Product not found", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getProductByGategory(Integer id) {
		try {
//			if (jwtFilter.isAdmin()) {
//			List<ProductWrapper> productByCategory = prodRepo.getProductByCategory(id);
//			return new ResponseEntity<List<ProductWrapper>>(productByCategory, HttpStatus.BAD_REQUEST);

//			}
//			return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
		try {
			Product product = prodRepo.findById(id).orElseThrow(()->new RuntimeException());
			 ProductWrapper prod=this.getProductWrapperFromProduc(product);
			if (!Objects.isNull(prod)) {
				return new ResponseEntity<ProductWrapper>(prod, HttpStatus.OK);
			}
			return new ResponseEntity<ProductWrapper>(new ProductWrapper(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ProductWrapper>(new ProductWrapper(), HttpStatus.BAD_REQUEST);
	}

	private ProductWrapper getProductWrapperFromProduc(Product product) {
		ProductWrapper prod=new ProductWrapper();
		prod.setCategoryName(product.getCategory().getCategoryName());
		prod.setDescription(product.getDescription());
		prod.setName(product.getName());
		prod.setStatus(product.getStatus());
		prod.setPrice(product.getPrice());
		prod.setImageUrl(product.getImageUrl());
		prod.setProductId(product.getProductId());
		
		return prod;
	}

	@Override
	public ResponseEntity<Page<Product>> getProducts(int page, int size) {
		  Page<Product> all = prodRepo.findAll(PageRequest.of(page, size));
		return new ResponseEntity<Page<Product>>(all, HttpStatus.OK);
	}

	


}
