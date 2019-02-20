package com.sale.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.InventoryDao;
import com.sale.app.dao.ProductDao;
import com.sale.app.models.Product;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	/*@Autowired
	private InventoryDao inventoryDao;*/
	
	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value="/addInventory", method = RequestMethod.GET)
	public String addInventory(Model model){
		List<Product> productList = productDao.getAllProduct();
		model.addAttribute("productList", productList);
		return "inventory/addInventory";
	}
	
	@RequestMapping(value="/addInventory", method=RequestMethod.POST)
	public String addInventory(Model model,@RequestParam(value="productName")String productName){
		
	try{
		Product product = new Product(productName);
		if (productName.equals("")) {
			throw new Exception("Requied field is missing");
		}
		productDao.addProduct(product);
		
	}
	
	catch(Exception ex){
		ex.printStackTrace();
		return "inventory/addProduct";
	}
	
	return "inventory/addProduct";
	}
	
}
