package com.sale.app.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.SupplierDao;

import com.sale.app.models.Supplier;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierDao supplierDao;
	
	@RequestMapping(value = "/createSupplier", method = RequestMethod.GET)
	public String createSupplier(Model model) {
		getAllSupplierFmDb(model);
		return "supplier/createSupplier";
	}
	
	private void getAllSupplierFmDb(Model model) {
		List<Supplier> supplierList = supplierDao.getAllSupplier();
		model.addAttribute("supplierList", supplierList);
		System.out.println("all supplierList  list");
	}
	
	@RequestMapping(value = "/createSupplier", method = RequestMethod.POST)
	public String createSupplier(Model model, @RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email,@RequestParam(value = "company") String company,
			@RequestParam(value = "street") String street,@RequestParam(value = "city") String city,
			@RequestParam(value = "state") String state) {
		try {
			System.out.println("Before create a supplier");
			Supplier supplier = new Supplier(name, email,company,street,city,state);

			if (name.equals("") || email.equals("") || company.equals("")||street.equals("")||city.equals("")||state.equals("")) {
				throw new Exception("Requied field is missing");
			}
			supplierDao.createSupplier(supplier);;
			System.out.println("After create a supplier");
			getAllSupplierFmDb(model);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "/supplier/createSupplier";
		}

		return "/supplier/createSupplier";
	}

	
}
