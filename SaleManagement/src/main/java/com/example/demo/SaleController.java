package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String logincheck(@RequestParam("mail_address") String mail_address,
    		@RequestParam("password") String password,
    		Model model,
    		SaleRequest saleRequest) {

    	Optional<Login> user = saleService.findOne(mail_address);
    	if(user.isPresent()) {
    		if(user.get().getPassword().equals(password)) {
    	    	List<Client> list = saleService.selectAll();
    	        model.addAttribute("list", list);
    			return "main";
    		}else {
            	model.addAttribute("mail_address",
            			mail_address);
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

    @PostMapping(value="/insert")
    public String insert(Model model) {
        model.addAttribute("saleRequest", new SaleRequest());
    	return "add";
    }

    @PostMapping(value="/check")
    public String check(@RequestParam("client") String client,
    		@RequestParam("order_date") String order_date,
    		@RequestParam("s_number") String s_number,
    		@RequestParam("subject") String subject,
    		@RequestParam("quantity") String quantity,
    		@RequestParam("delivery_date") String delivery_date,
    		@RequestParam("due_date") String due_date,
    		@RequestParam("billing_date") String billing_date,
    		@RequestParam("estimated_amount") String estimated_amount,
    		@RequestParam("order_amount") String order_amount,
    		@RequestParam("status_number") String status_number,
    		@RequestParam("remarks") String remarks,
    		Model model ,
    		SaleRequest saleRequest) {
    	model.addAttribute("client",  client);
    	model.addAttribute("order_date",  order_date);
    	model.addAttribute("s_number",  s_number);
    	model.addAttribute("subject",  subject);
    	model.addAttribute("quantity",  quantity);
    	model.addAttribute("delivery_date",  delivery_date);
    	model.addAttribute("due_date",  due_date);
    	model.addAttribute("billing_date",  billing_date);
    	model.addAttribute("estimated_amount",  estimated_amount);
    	model.addAttribute("order_amount",  order_amount);
    	model.addAttribute("status_number",  status_number);
    	model.addAttribute("remarks",  remarks);
    	return "addcheck";
    }

    @PostMapping(value = "/checkok")
    public String create(Model model,
    		SaleRequest saleRequest) {
    	saleService.create(saleRequest);

    	List<Client> list = saleService.selectAll();
        model.addAttribute("list", list);
		return "main";

    }

    @PostMapping(value="/delete/{no}")
    public String delete(@PathVariable String no, Model model) {
        Client list = saleService.getOne(no);
        model.addAttribute("list", list);
    	return "delete";
    }
}
