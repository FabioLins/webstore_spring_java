package org.packt.webstore.repository;

import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	
	Product getProductById(String productID);
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByFilter(String category, Map<String, List<Double>> priceRange, String manufacturer);
	
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	void updateStock(String productId, long noOfUnits);
	
	void addProduct(Product product);

}
