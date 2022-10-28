package ccom.training.pms.galaxe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccom.training.pms.galaxe.dao.ProductDAO;
import ccom.training.pms.galaxe.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productdao;
	


 	@Override
	public String saveProduct(Product product) {
		//security checking - 10 lines
 		//logging           - 5  lines
 		//transaction
 		
 		//System.out.println("Save Product called");
 		
		if(product.getPrice() <0 | product.getQuantintyOnHand() <0)
		{
			return "Product price or QOH cannot be negative. Not Saved";
		}
		else {
			productdao.save(product);
			return "Product Saved Successfully";
		}

	}



	@Override
	public String updateProduct(Product product) {
		if(product.getPrice() <0 | product.getQuantintyOnHand() <0)
		{
			return "Product price or QOH cannot be negative. Not Updated";
		}
		else {
			productdao.save(product);
			return "Product Updated Successfully";
		}
	}



	@Override
	public String deleteProduct(int productId) {
		productdao.deleteById(productId);
		return "Product deleted successfully";
	}



	@Override
	public Product getProduct(int productId) {
		Optional<Product> product= productdao.findById(productId);
		return product.get();
	}



	@Override
	public List<Product> getProduct() {
		return (List<Product>) productdao.findAll();
	}



	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> product= productdao.findById(productId);
		return product.isPresent();
	}


	// To be continued     part - 2 online
	@Override
	public List<Product> searchProduct(String productName) {
		return productdao.findByProductName(productName);
	}



	@Override
	public List<Product> searchProduct(String productName, int price, int qoh) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Product> searchProduct(int min, int max) {
		return productdao.findByPriceBetween(min, max);
	}



	@Override
	public List<Product> checkStockStatus(int minStock) {
		return productdao.findByQuantintyOnHandGreaterThan(minStock);
	}
}
