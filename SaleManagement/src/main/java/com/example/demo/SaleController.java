package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
     * メイン画面表示
     *
     * @param model
     * @param saleRequest
     * @param pageable
     * @return
     */
    @GetMapping(value="/main")
    public String main(Model model ,
    		SaleRequest saleRequest,
    		@PageableDefault(size = 10) Pageable pageable) {

    	Page<Client> list = saleService.selectAll(pageable);
        SaleWrapper<Client> page = new SaleWrapper<Client>(list);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        List<Client> status = saleService.statusAll();
        model.addAttribute("status", status);
        List<Status> statusNumber = saleService.statusNumberAll();
        model.addAttribute("statusNumber", statusNumber);

		return "main";
    }

    /**
     * パスワードの照合
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
    		SaleRequest saleRequest,
    		@PageableDefault(size = 10) Pageable pageable) {

    	Optional<Login> user = saleService.findOne(mail_address);
    	//メールアドレスが存在した場合
    	if(user.isPresent()) {
    		//パスワードが正しい場合
    		if(user.get().getPassword().equals(password)) {
    			return "redirect:/main";
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
     * 新規登録処理
     *
     * @param model
     * @param saleRequest
     * @return
     */
    @PostMapping(value = "/checkok")
    public String create(Model model,
    		SaleRequest saleRequest,
    		@PageableDefault(size = 10) Pageable pageable) {
    	saleService.create(saleRequest);

		return "redirect:/main";

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
    		@PageableDefault(size = 10) Pageable pageable,
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

    	return "redirect:/main";
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
        if(!list.getOrder_date().equals("")){
        	list.setOrder_date(list.getOrder_date().substring(0,4) + "/" + list.getOrder_date().substring(4,6) + "/" + list.getOrder_date().substring(6,8));
        }
        if(!list.getDelivery_date().equals("")){
        	list.setDelivery_date(list.getDelivery_date().substring(0,4) + "/" + list.getDelivery_date().substring(4,6) + "/" + list.getDelivery_date().substring(6,8));
        }
        if(!list.getDue_date().equals("")){
        	list.setDue_date(list.getDue_date().substring(0,4) + "/" + list.getDue_date().substring(4,6) + "/" + list.getDue_date().substring(6,8));
        }
        if(!list.getBilling_date().equals("")){
        	list.setBilling_date(list.getBilling_date().substring(0,4) + "/" + list.getBilling_date().substring(4,6) + "/" + list.getBilling_date().substring(6,8));
        }
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
    public String editok(Model model ,
    		SaleRequest saleRequest,
    		@PageableDefault(size = 10) Pageable pageable) {
    	saleService.edit(saleRequest);

    	return "redirect:/main";
    }

    /**
     * 検索処理
     *
     * @param model
     * @param pageable
     * @param saleRequest
     * @return
     */
    @PostMapping(value = "/search")
    public String search(Model model,
    		@PageableDefault(size = 10) Pageable pageable,
    		SaleRequest saleRequest) {
    	saleRequest.searchSomething = saleRequest.searchSomething.replace("　","");
    	saleRequest.searchSomething = saleRequest.searchSomething.trim();
        	Page<Client> list = saleService.searchAll(pageable, saleRequest);
            SaleWrapper<Client> page = new SaleWrapper<Client>(list);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
	        List<Client> status = saleService.statusAll();
	        model.addAttribute("status", status);
	        List<Status> statusNumber = saleService.statusNumberAll();
	        model.addAttribute("statusNumber", statusNumber);
        return "main";
    }

    /**
     * ページング機能
     *
     * @param model
     * @param pageable
     * @param saleRequest
     * @return
     */
    @PostMapping(value = "/page")
    public String page(Model model,
    		@PageableDefault(size = 10) Pageable pageable,
    		SaleRequest saleRequest) {
        Page<Client> list=saleService.searchAll(pageable, saleRequest);
        SaleWrapper<Client> page = new SaleWrapper<Client>(list);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        List<Client> status = saleService.statusAll();
        model.addAttribute("status", status);
        model.addAttribute("searchSomething", saleRequest.searchSomething);
        List<Status> statusNumber = saleService.statusNumberAll();
        model.addAttribute("statusNumber", statusNumber);
        return "main";
    }
}