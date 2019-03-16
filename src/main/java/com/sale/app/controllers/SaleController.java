package com.sale.app.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sale.app.dao.SaleDao;
import com.sale.app.dao.SalesmanDao;
import com.sale.app.models.Inventory;
import com.sale.app.models.Sale;
import com.sale.app.models.Salesman;

@Controller
@RequestMapping("/sale")
public class SaleController {
	@Autowired
	private SaleDao saleDao;

	@Autowired
	private SalesmanDao salemanDao;

	@RequestMapping(value = "/addsale", method = RequestMethod.GET)
	public String addsale(Model model) {
		return "/sale/addsale";
	}

	
	@RequestMapping(value = "/addsale", method = RequestMethod.POST)
	public ResponseEntity<?> addsale(@RequestBody List<Inventory> inventoryList, HttpSession httpssion) {
		Salesman operator = (Salesman) httpssion.getAttribute("loginOperator");

		Salesman salesman = salemanDao.getSalesman(operator.getId());
		//Salesman salesman = salemanDao.getSalesman(15L);

		for (Inventory inventory : inventoryList) {
			System.out.println(inventory.getQuantity());
			saleDao.saveTranjection(inventory.getQuantity(), inventory.getSku(), salesman);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
