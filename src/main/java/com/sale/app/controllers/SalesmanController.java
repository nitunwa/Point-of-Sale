package com.sale.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.SalesmanDao;
import com.sale.app.models.Salesman;

@Controller
@RequestMapping("/salesman")
public class SalesmanController {

	@Autowired
	private SalesmanDao salesmanDao;
	
	@RequestMapping(value="/createSalesman",method=RequestMethod.GET)
	public String createUser(Model model) {
		return "salesman/createSalesman";
	}

	@RequestMapping(value="/createSalesman", method=RequestMethod.POST)
	public String createSalesman(@RequestParam(value="salesmanName")String salesmanName,@RequestParam(value="password")String password){
		try{
			System.out.println("before create a salesman");
			Salesman salesman = new Salesman(salesmanName,password);
			
			if(salesmanName.equals("")||password.equals("")){
				throw new Exception("Requied field is missing");
			}
			salesmanDao.createSalesman(salesman);
			System.out.println("After create a salesman");
				
		}
		catch(Exception ex){
			ex.printStackTrace();
			return"/salesman/createSalesman";
		}
		
		return "redirect:/salesman/salesmanListReport";
	}
	@RequestMapping(value="/salesmanListReport")
	public String getAllsalesmanList(Model model){
		List<Salesman> salesmanList= salesmanDao.getAllSalesman();
		model.addAttribute("salesmanList", salesmanList);
		return "/salesman/salesmanListReport";
	}

}
