package com.sale.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.SaleDao;
import com.sale.app.models.Sale;

@Controller
@RequestMapping("/reciept")
public class RecieptController {
	@Autowired
	private SaleDao saleDao;
	
	@RequestMapping(value = "/showReciept", method = RequestMethod.GET)
	public String showReciept(Model model,@RequestParam("saleId") Long saleId) {
		System.out.println("Start RecieptController.showReciept() param: " + saleId );
		Sale sale = saleDao.getSale(new Sale(saleId));
		model.addAttribute("sale" , sale);
		return "/sale/reciept";
	}
}
