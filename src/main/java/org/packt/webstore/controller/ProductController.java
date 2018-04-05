package org.packt.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.packt.webstore.domain.Product;
import org.packt.webstore.exception.NoProductsFoundUnderCategoryException;
import org.packt.webstore.exception.AddressNotFoundException;
import org.packt.webstore.service.ProductService;
import org.packt.webstore.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("market")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		
		binder.setValidator(productValidator);
		
		binder.setAllowedFields("productId",
				"name",
				"unitPrice",
				"description",
				"manufacturer",
				"category",
				"unitsInStock",
				"condition",
				"productImage",
				"productPdf",
				"language");

	}
	
	@GetMapping("/products")
	public String list(Model model) {
		/*
		Product iphone = new Product("P1234","iPhone 6s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 6s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
		iphone.setCategory("Smartphone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		 */
		
		model.addAttribute("products", this.productService.getAllProducts());
		
		return "products";
		
	}
	
	@GetMapping("/update/stock")
	public String updateStock(Model model) {
		this.productService.updateAllStock();
		return "redirect:/market/products";
	}	
	
	@GetMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String category) {
		List<Product> products = productService.getProductsByCategory(category);
		if(products == null || products.isEmpty()) {
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("products", this.productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@GetMapping("/product")
	public String getProductById(@RequestParam("id") String productID, Model model) {
		model.addAttribute("product", this.productService.getProductById(productID));
		return "product";
	}
	
	@GetMapping("/products/{category}/{price}")
	public String filterProducts(Model model, @PathVariable("category") String productCategory,
			@MatrixVariable(pathVar="price") Map<String, List<Double>> priceRange, @RequestParam("brand") String manufacturer) {
		model.addAttribute("products", this.productService.getProductsByFilter(productCategory, priceRange, manufacturer));
		
		return "products";
	}
	
	@GetMapping("/products/add")
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}
	
	@PostMapping("/products/add")
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "addProduct";
		}
		
		String[] suppressedFields = result.getSuppressedFields();
		if(suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " 
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = newProduct.getProductImage();
		MultipartFile productPdf = newProduct.getProductPdf();
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
			} catch(Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		
		if(productPdf != null && !productPdf.isEmpty()) {
			try {
				productPdf.transferTo(new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
			} catch(Exception e) {
				throw new RuntimeException("Product Pdf saving failed", e);
			}
		}
		
		this.productService.addProduct(newProduct);
		
		return "redirect:/market/products";
	}
	
	@GetMapping("/products/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, AddressNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		
		return mav;
	}

}
