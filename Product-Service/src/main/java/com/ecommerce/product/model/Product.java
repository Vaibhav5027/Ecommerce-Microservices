package com.ecommerce.product.model;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//@NamedQuery(name="Product.getAllProducts", query="select new com.cafe.wrappers.ProductWrapper"
//		+ "(p.id,p.name,p.description,p.status,p.price,p.category.id,p.category.name)  from Product p" )
//
//@NamedQuery(name="Product.updateStatus", query ="Update Product p set p.status=: status where p.id=:id" )
//
//@NamedQuery(name="Product.getProductByCategory" ,query = "Select new com.cafe.wrappers.ProductWrapper(p.name, p.id) from Product p  where p.category.id= :id")
//@NamedQuery(name="Product.getProductById",query = "Select new com.cafe.wrappers.ProductWrapper(p.id,p.name,p.description,p.status,p.price,p.category.id,p.category.name) from Product p where p.id= :id" )
//@Entity
//@DynamicUpdate
//@DynamicInsert
//@Data
//@Table(name="product")

@Entity
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 123456L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "price")
	private Integer price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	private String imageUrl;

//	private int quantity;
}
