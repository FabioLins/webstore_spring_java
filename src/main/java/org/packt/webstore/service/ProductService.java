package org.packt.webstore.service;

import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProductById(String productID);
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByFilter(String category, Map<String, List<Double>> priceRange, String manufacturer);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	void updateAllStock();
	
	void addProduct(Product product);
	
}
