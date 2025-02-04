package com.ecommerce.product.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.product.model.CartItems;
import com.ecommerce.product.repo.CartRepo;

@Service
public class CartService {
   
	@Autowired
	private CartRepo cartRepo;
	
	public ResponseEntity<?> addToCart(Map<String, String> requestParam) {
		if(validateCartItems(requestParam)) {
			if((String)requestParam.get("email")!=null) {
				 CartItems byUsernameAndId = findByEmailAndId((String)requestParam.get("email"),Long.parseLong(requestParam.get("productId")) );
				 if(byUsernameAndId==null) {
					 CartItems cartItems=  cartItemsFromRequest(requestParam);
						cartRepo.save(cartItems);
						return ResponseEntity.ok().body("{\"message\":\"added to cart\"}");
				 }
				 else {
					 int quantity = byUsernameAndId.getQuantity();
					 byUsernameAndId.setQuantity(++quantity);
					 cartRepo.save(byUsernameAndId);
					 return ResponseEntity.ok().body("{\"message\":\"added to cart\"}");
				 }
			}else {
				 return ResponseEntity.ok().body("{\"message\":\"please login first\"}");
			}
			
		}
		else {
			return ResponseEntity.badRequest().body("Invalid cart items");
		}
	}

	private CartItems cartItemsFromRequest(Map<String, String> requestParam) {
		CartItems cart=new CartItems();
		cart.setEmail((String) requestParam.get("email"));
		cart.setName((String) requestParam.get("name"));
		cart.setPrice( Double.parseDouble( requestParam.get("price")));
		cart.setProductId( Long.parseLong(requestParam.get("productId")));
		cart.setQuantity(cart.getQuantity()) ;
		cart.setImageUrl((String) requestParam.get("imageUrl"));
		return cart;
	}

	private Boolean validateCartItems(Map<String, String> requestParam) {
		return true;
		
	}

	public ResponseEntity<List<CartItems>> getAllCarts(String email) {
		List<CartItems> carts= cartRepo.findByEmail(email);
		return  new ResponseEntity<List<CartItems>>(carts,HttpStatus.OK);
	}
    public CartItems findByEmailAndId(String username,long productId) {
    	CartItems byUsernameAndProductId = cartRepo.findByEmailAndProductId(username,productId);
    	return byUsernameAndProductId;
    }

 
	public ResponseEntity<?> deleteCartItem(Map<String, String> requestParam) {
		cartRepo.deleteByProductIdAndEmail(Long.parseLong(requestParam.get("id")),(String) requestParam.get("username"));
		 return ResponseEntity.ok().body("{\"message\":\"item removed\"}");
	}
}
