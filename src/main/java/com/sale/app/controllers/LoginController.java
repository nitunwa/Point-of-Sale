package com.sale.app.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sale.app.dao.SalesmanDao;
import com.sale.app.models.Salesman;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private SalesmanDao salesmanDao;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Model model) {
		return "login/login";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signin(Model model, @RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password, HttpSession httpSession) {
		try {
			Salesman salesman = salesmanDao.getByEmail(email);
			System.out.println(salesman.getEmail());
			
			if (salesman.getPassword().equals(password)) {
				System.out.println("Valid  Operator----------------------");
				httpSession.setAttribute("loginOperator", salesman);
				Salesman sa= (Salesman) httpSession.getAttribute("loginOperator");
				System.out.println(sa.getEmail());
						
			} else {
				System.out.println("Invalid Operator");
				return "login/login";
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return "redirect:/inventory/addInventory";
	}
}
