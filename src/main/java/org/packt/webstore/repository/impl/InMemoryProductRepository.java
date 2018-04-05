package org.packt.webstore.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.packt.webstore.domain.Product;
import org.packt.webstore.exception.AddressNotFoundException;
import org.packt.webstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Product> getAllProducts() {

		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM Products", params, new ProductMapper());
		
		return result;
	}
	
	private static final class ProductMapper implements RowMapper<Product> {
		
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));

			return product;
		}
		
	}

	@Override
	public void updateStock(String productId, long noOfUnits) {

		String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productId);
		jdbcTemplate.update(SQL, params);
		
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", category);
		
		return this.jdbcTemplate.query(SQL, params, new ProductMapper());
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY IN (:categories) AND MANUFACTURER IN (:brands) ";
		
		return this.jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	}

	@Override
	public Product getProductById(String productID) {
		String SQL = "SELECT * FROM PRODUCTS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		try {
			return this.jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
		} catch(DataAccessException e) {
			throw new AddressNotFoundException(productID);
		}
	}

	@Override
	public List<Product> getProductsByFilter(String category, Map<String, List<Double>> priceRange, String manufacturer) {
		String SQL = "SELECT * FROM PRODUCTS WHERE (CATEGORY = :category) AND (UNIT_PRICE BETWEEN :low AND :high)"
				+ " AND (MANUFACTURER = :manufacturer)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", category);
		params.put("low", priceRange.get("low"));
		params.put("high", priceRange.get("high"));
		params.put("manufacturer", manufacturer);
		
		return this.jdbcTemplate.query(SQL, params, new ProductMapper());
	}

	@Override
	public void addProduct(Product product) {
		String SQL = "INSERT INTO PRODUCTS(ID,"  
				+ "	NAME,"  
				+ "	DESCRIPTION,"  
				+ "	UNIT_PRICE, "  
				+ "	MANUFACTURER, "  
				+ "	CATEGORY, "  
				+ "	CONDITION, "  
				+ "	UNITS_IN_STOCK, "  
				+ "	UNITS_IN_ORDER, "  
				+ "	DISCONTINUED) VALUES (:id, :name, :desc, :price, "
				+ ":manufacturer, :category, :condition, :inStock, :inOrder, :discontinued)";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", product.getProductId());
		params.put("name", product.getName());
		params.put("desc", product.getDescription());
		params.put("price", product.getUnitPrice());
		params.put("manufacturer", product.getManufacturer());
		params.put("category", product.getCategory());
		params.put("condition", product.getCondition());
		params.put("inStock", product.getUnitsInStock());
		params.put("inOrder", product.getUnitsInOrder());
		params.put("discontinued", product.isDiscontinued());
		
		this.jdbcTemplate.update(SQL, params);
	}

}
