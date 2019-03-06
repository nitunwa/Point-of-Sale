package com.sale.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.InventoryDao;
import com.sale.app.dao.ProductDao;
import com.sale.app.dao.SalesmanDao;
import com.sale.app.dao.SupplierDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Product;
import com.sale.app.models.Salesman;
import com.sale.app.models.Supplier;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	/*@Autowired
	private InventoryDao inventoryDao;*/
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private SalesmanDao salesmanDao;
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@RequestMapping(value="/addInventory", method = RequestMethod.GET)
	public String addInventory(Model model){
		List<Product> productList = productDao.getAllProduct();
		model.addAttribute("productList", productList);
		List<Supplier> supplierList = supplierDao.getAllSupplier();
		model.addAttribute("supplierList", supplierList);
		
		return "inventory/addInventory";
	}
	
	@RequestMapping(value="/addInventory", method=RequestMethod.POST)
	public String addInventory(Model model,@RequestParam(value="supplierId") Long supplierId,@RequestParam(value="quantity") int quantity,
			@RequestParam(value="price") double price,@RequestParam(value="batchNum") int batchNum,
			@RequestParam(value="productId") Long productId, @RequestParam(value="sku") String sku,HttpSession httpSession){
		
	try{
		Salesman user =(Salesman) httpSession.getAttribute("loginOperator");
		//System.out.println(salesman.getEmail());
		Product product = productDao.getProduct(productId);
		Supplier supplierFmDb = supplierDao.getsupplier( supplierId );
		Salesman salesman=salesmanDao.getSalesman(user.getId());
		
		Inventory inventory = new Inventory(product,quantity,price,batchNum,supplierFmDb,salesman,sku);
		/*if (product.getProductName().equals("") || quantity<=0 || price<=0.0||supplier.getName().equals("")||salesman.getId().equals("")||sku.equals("")) {
			throw new Exception("Requied field is missing");
		}*/
		inventoryDao.addInventory(inventory);
	}
	
	
	catch(Exception ex){
		ex.printStackTrace();
		return "/inventory/addProduct";
	}
	
	return "/inventory/addInventory";
	}
	
}
