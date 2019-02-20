package com.sale.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.ProductDao;
import com.sale.app.models.Product;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model){
		getAllProduct(model);
		return "inventory/addProduct";
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String addProduct(Model model,@RequestParam(value="productName")String productName){
		
	try{
		Product product = new Product(productName);
		if (productName.equals("")) {
			throw new Exception("Requied field is missing");
		}
		productDao.addProduct(product);
		getAllProduct(model);
	}
	
	catch(Exception ex){
		ex.printStackTrace();
		return "inventory/addProduct";
	}
	
	return "inventory/addProduct";
	}
	
	private void getAllProduct(Model model){
		List<Product> productList= productDao.getAllProduct();
		for(Product product :productList){
			System.out.println(product.getProductName());
		}
		model.addAttribute("productList", productList);
	}
	
	@RequestMapping(value="/deleteById", method=RequestMethod.GET)
	public String deleteById(Model model,@RequestParam(value="productId")Long  productId){
		try{
			Product product = new Product(productId);
			productDao.delete(product);
			getAllProduct(model);
		}catch(Exception ex){
			ex.printStackTrace();
			return "inventory/addProduct";
		}
		return "inventory/addProduct";
	}
}
