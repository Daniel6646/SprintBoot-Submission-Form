package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Customers;
import com.example.demo.service.CustomerRepo;

@Controller
public class FormController {

	@Autowired
	CustomerRepo repo;
	
	
	@RequestMapping("/")
	public String edureka(Customers customer) {
	
	return "edureka";
	}
	
	@RequestMapping("/details")
	public String details(Customers customers) {
	repo.save(customers);
		
	return "edureka";
	}
	
	@RequestMapping("/getdetails")
	public String getDetails() {
	
	return "ViewCustomers";
	}
	
	
	
//	@PostMapping("details")
//	public String viewDetails(@RequestParam("cid") String cid,@RequestParam("cname") String cname,@RequestParam("cemail") String cemail, ModelMap modelmap) 	
//	{ 
//		modelmap.put("cid", cid);
//		modelmap.put("cname", cname);
//		modelmap.put("cemail", cemail);
//		
//		return"ViewCustomers";
//	}
	
	@PostMapping("/getdetails")
	public ModelAndView viewDetails(@RequestParam int cid) 	
	{ 
		ModelAndView mv = new ModelAndView("Retrieve"); //jsp file
		
		Customers customers= repo.findById(cid).orElse(null);
		mv.addObject(customers);
		return mv;
	}

	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getCustomers() {
		
		return repo.findAll();
				
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public Optional<Customers> getCustomers2(@PathVariable("cid") int cid) {
		
		return repo.findById(cid);//what is optional if we need a specific detail then we use optional,when return type is string give string if it gives error type mismatch cannot convert into string if there is a tostring then remove it
				
	}

	@PostMapping("/customers")
	public Customers getCustomers3(@RequestBody Customers customers) {
			
		repo.save(customers);
		return customers;
				
	}
	
	@DeleteMapping("/customers/{cid}")
	public Customers getCustomers4(@PathVariable("cid") int cid) {
			
		Customers cust =repo.getOne(cid);
		repo.delete(cust);
		return cust;
				
	}
	
	@PutMapping(path="/customers", consumes= {"application/json"})
	public Customers getCustomers5(@RequestBody Customers customers) {
			
		 repo.save(customers);
		return customers;
				
	}
	
  }
