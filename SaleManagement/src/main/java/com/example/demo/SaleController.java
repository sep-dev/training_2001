package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaleController {
    @Autowired
    SaleService saleService;

    @GetMapping(value = "/sale")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String logincheck(@RequestParam("mail_address") String mail_address, @RequestParam("password") String password, Model model, SaleRequest saleRequest) {

    	Optional<Login> user = saleService.findOne(mail_address);
    	if(user.isPresent()) {
    		if(user.get().getPassword().equals(password)) {
    	    	List<Client> list = saleService.selectAll();
    	        model.addAttribute("list", list);
    			return "main";
    		}else {
            	model.addAttribute("mail_address",  mail_address);
            	model.addAttribute("password",  password);
            	return "login";
    		}
    	}
    	return "login";
    	//Login user = saleService.getOne(mail_address);
    	//Login user = saleService.count(mail_address);
    	//boolean findmail =saleService.existsById(mail_address);
    	//if(user.getPassword().equals(password)) {
    	//if(findmail) {
    	/*	if(user.getPassword().equals(password)) {
           	return "main";
    		}else {
            	model.addAttribute("mail_address",  mail_address);
            	model.addAttribute("password",  password);
            	return "login";
    		}
    	}else {
        	model.addAttribute("mail_address",  mail_address);
        	model.addAttribute("password",  password);
        	return "login";
    	}*/
    }
}
