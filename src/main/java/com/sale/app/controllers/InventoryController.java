package com.sale.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sale.app.dao.InventoryDao;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	/*@Autowired
	private InventoryDao inventoryDao;*/
	
	@RequestMapping(value="/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model){
		return "inventory/addInventory";
	}
	
}
