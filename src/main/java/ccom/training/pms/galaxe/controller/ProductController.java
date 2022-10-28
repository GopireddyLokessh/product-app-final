package ccom.training.pms.galaxe.controller;
 import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import ccom.training.pms.galaxe.dao.Productdao;
import ccom.training.pms.galaxe.model.Product;
import ccom.training.pms.galaxe.service.ProductService;
import ccom.training.pms.galaxe.service.ProductServiceImpl;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductServiceImpl productService;
	
	 public ProductController() {
		// TODO Auto-generated constructor stub
	}
	 
	 @GetMapping				//localhost:9090/product
	 public ResponseEntity<List<Product>> getProducts() {
			List<Product> products = productService.getProduct();
					
			ResponseEntity<List<Product>> responseEntity;

			if(products.isEmpty()) {
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
			}
			else
			{
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
			}
			return responseEntity;
		}
	 
	 @GetMapping("{productId}")  //http://localhost:9090/product/192
	 public Product getProduct(@PathVariable("productId")Integer productId){
		return  productService.getProduct(productId);
	 }
	 
	 @DeleteMapping("{productId}")  //http://localhost:9090/product/192   -DELETE
	 public String deleteProduct(@PathVariable("productId")Integer productId){
		return "Deleting a single product with product id:"+productId;
	 }
	 
	 @PostMapping()  //http://localhost:9090/product    POST   - BODY(product)
	 public String saveProduct(@RequestBody Product product){
		 productService.saveProduct(product);
		return "Updating a single product with product details:"+product;
	 }
	 
	 @PutMapping()  //http://localhost:9090/product   -PUT      -BODY(product)
	 public String updateProduct(@RequestBody Product product){
		return "Updating a single product with product details:"+product;
	 }
	 
	 @GetMapping("searchByProductName/{productName}") // url - localhost:9090/product/2435678
		public ResponseEntity<List<Product>> getProductByName(@PathVariable("productName") String productName) {
		
			ResponseEntity<List<Product>> responseEntity;
			List<Product> products  = productService.searchProduct(productName);
			if(products.isEmpty()) {
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
			}
			else
			{
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
			}
			return responseEntity;
		}
	 
	 
	 @GetMapping("checkStockStatus/{minStock}")
		public ResponseEntity<List<Product>> getStockStatus(@PathVariable("minStock")Integer minStock){
			ResponseEntity<List<Product>> responseEntity;
			List<Product> products=productService.checkStockStatus(minStock);
			if(products.isEmpty()) {
				responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
			}
			else {
				responseEntity=new ResponseEntity<List<Product>>(products,HttpStatus.OK);
			}
			return responseEntity;
		}
	 
	 
	 @GetMapping("searchByRange/{min}/{max}") // url - localhost:9090/product/2435678
		public ResponseEntity<List<Product>> getProductByRange(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
		
			ResponseEntity<List<Product>> responseEntity;
			List<Product> products  = productService.searchProduct(min,max);
			if(products.isEmpty()) {
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT);
			}
			else
			{
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
			}
			return responseEntity;
		}
	 
	 

}
