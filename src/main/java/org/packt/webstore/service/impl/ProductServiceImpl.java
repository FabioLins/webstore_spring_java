package org.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Product;
import org.packt.webstore.repository.ProductRepository;
import org.packt.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.getAllProducts();
	}
	
	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if(product.getUnitsInStock() < 500) {
				productRepository.updateStock(product.getProductId(),
						product.getUnitsInStock() + 1000);
			}
		}
	}
	
	@Override
	public List<Product> getProductsByCategory(String category) {
		return this.productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return this.productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID) {
		return this.productRepository.getProductById(productID);
	}

	@Override
	public List<Product> getProductsByFilter(String category, Map<String, List<Double>> priceRange, String manufacturer) {
		return this.productRepository.getProductsByFilter(category, priceRange, manufacturer);
	}

	@Override
	public void addProduct(Product product) {
		this.productRepository.addProduct(product);
	}

}
