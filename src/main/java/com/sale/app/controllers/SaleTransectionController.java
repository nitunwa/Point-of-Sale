package com.sale.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sale.app.dao.SaleDao;
import com.sale.app.dao.SalesmanDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Sale;
import com.sale.app.models.Salesman;

@Controller
@RequestMapping("/testSale")
public class SaleTransectionController {
	@Autowired
	private SaleDao saleDao;

	@Autowired
	private SalesmanDao salemanDao;

	@RequestMapping(value = "/saveSale", method = RequestMethod.GET)
	public String saveSale(Model model) {
		return "/sale/saveSale";
	}

	
	public List<Inventory> getInvertoryListFromStr(String saleList) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Inventory[] inventory=mapper.readValue(saleList, Inventory[].class);
		List<Inventory> inventoryList = Arrays.asList(inventory);
		return inventoryList;
	}
	
	@RequestMapping(value = "/saveSale", method = RequestMethod.POST)
	public String saveSale(@RequestParam(value = "saleList") String saleList, HttpSession httpssion) {
		/*Salesman operator = (Salesman) httpssion.getAttribute("loginOperator"); 

        ;
		Salesman salesman = salemanDao.getSalesman(operator.getId());*/
		
		Salesman salesman = salemanDao.getSalesman(15L);
		 List<Inventory> inventoryList;
		try {
			inventoryList = getInvertoryListFromStr(saleList);
			for (Inventory inventory : inventoryList) {
				System.out.println(inventory.getQuantity());
				saleDao.saveTranjection(inventory.getQuantity(), inventory.getSku(), salesman);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return "/sale/saveSale";
	}

}
