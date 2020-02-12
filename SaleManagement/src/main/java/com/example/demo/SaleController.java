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

    /**
     * ログイン画面表示
     *
     * @return
     */
    @GetMapping(value = "/sale")
    public String login() {
        return "login";
    }

    /**
     * パスワードの照合・メインページへ遷移
     *
     * @param mail_address
     * @param password
     * @param model
     * @param saleRequest
     * @return
     */
    @PostMapping(value = "/login")
    public String logincheck(@RequestParam("mail_address") String mail_address,
    		@RequestParam("password") String password,
    		Model model,
    		SaleRequest saleRequest) {

    	Optional<Login> user = saleService.findOne(mail_address);
    	//メールアドレスが存在した場合
    	if(user.isPresent()) {
    		//パスワードが正しい場合
    		if(user.get().getPassword().equals(password)) {
    	    	List<Client> list = saleService.selectAll();
    	        model.addAttribute("list", list);
    			return "main";
    			//パスワードが異なる場合
    		}else {
            	model.addAttribute("mail_address",
            			mail_address);
            	model.addAttribute("password",  password);
            	return "login";
    		}
    	}
    	//メールアドレスが存在しない場合
    	return "login";
    }

    /**
     * 新規登録画面へ遷移
     *
     * @param model
     * @return
     */
    @PostMapping(value="/insert")
    public String insert(Model model) {
        model.addAttribute("saleRequest", new SaleRequest());
    	return "add";
    }

    /**
     * 新規登録確認画面へ遷移
     *
     * @param client
     * @param order_date
     * @param s_number
     * @param subject
     * @param quantity
     * @param delivery_date
     * @param due_date
     * @param billing_date
     * @param estimated_amount
     * @param order_amount
     * @param status_number
     * @param remarks
     * @param model
     * @param saleRequest
     * @return
     */
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

    /**
     * 新規登録処理・メインページへ遷移
     *
     * @param model
     * @param saleRequest
     * @return
     */
    @PostMapping(value = "/checkok")
    public String create(Model model,
    		SaleRequest saleRequest) {
    	saleService.create(saleRequest);

    	List<Client> list = saleService.selectAll();
        model.addAttribute("list", list);
		return "main";

    }

    /**
     * 削除画面へ遷移
     *
     * @param no
     * @param model
     * @return
     */
    @PostMapping(value="/delete/{no}")
    public String delete(@PathVariable String no, Model model) {
        Client list = saleService.getOne(no);
        model.addAttribute("list", list);
    	return "delete";
    }

    /**
     * 論理削除処理
     *
     * @param no
     * @param client
     * @param order_date
     * @param s_number
     * @param subject
     * @param quantity
     * @param delivery_date
     * @param due_date
     * @param billing_date
     * @param estimated_amount
     * @param order_amount
     * @param status_number
     * @param remarks
     * @param model
     * @param saleRequest
     * @return
     */
    @PostMapping(value="/delete")
    public String deleteok(@RequestParam("no") String no,
    		@RequestParam("client") String client,
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
    		Model model,
    		SaleRequest saleRequest) {
    	model.addAttribute("no",  no);
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
    	saleService.delete(saleRequest);

    	List<Client> list = saleService.selectAll();
        model.addAttribute("list", list);

    	return "main";
    }

    /**
     * 編集画面へ遷移
     *
     * @param no
     * @param model
     * @return
     */
    @PostMapping(value="/edit/{no}")
    public String edit(@PathVariable String no, Model model) {
        Client list=saleService.getOne(no);
        model.addAttribute("list", list);
    	return "edit";
    }

	/**
	 * 編集確認画面へ遷移
	 *
	 * @param saleRequest
	 */
    @PostMapping(value="/checkedit")
    public String edit(@RequestParam("no") String no,
    		@RequestParam("client") String client,
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
    		Model model,
    		SaleRequest saleRequest) {
    	model.addAttribute("no",  no);
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
    	return "editcheck";
	}

    /**
     * 編集処理
     *
     * @param model
     * @param saleRequest
     * @return
     */
    @PostMapping(value="/editcheckok")
    public String editok(Model model ,SaleRequest saleRequest) {
    	saleService.edit(saleRequest);

    	List<Client> list = saleService.selectAll();
        model.addAttribute("list", list);

    	return "main";
    }
}
