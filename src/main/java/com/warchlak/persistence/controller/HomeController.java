package com.warchlak.persistence.controller;

import com.warchlak.persistence.entity.Product;
import com.warchlak.persistence.repository.ProductRepository;
import com.warchlak.persistence.thymeleafObject.ItemForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController
{
	@Value("addSuccessMessage")
	private String successMessage;
	
	@Value("addErrorMessage")
	private String errorMessage;
	
	private final
	MessageSource messageSource;
	
	private final ProductRepository repository;
	
	@Autowired
	public HomeController(ProductRepository repository, MessageSource messageSource)
	{
		this.repository = repository;
		this.messageSource = messageSource;
	}
	
	@PostConstruct
	public void initialize()
	{
		successMessage = messageSource.getMessage("addSuccessMessage", null, null);
		errorMessage = messageSource.getMessage("addErrorMessage", null, null);
		log.info("-----> successMessage is: " + successMessage);
		log.info("-----> errorMessage is: " + errorMessage);
	}
	
	@RequestMapping
	public String showHomePage()
	{
		return "index";
	}
	
	@RequestMapping("list")
	public String listItems(Model model)
	{
		List<Product> products = new ArrayList<>();
		Iterable<Product> iterable = repository.findAll();
		iterable.forEach(products::add);
		
		log.info("-----> Passing product list with " + products.size() + " items to the itemsList view");
		
		model.addAttribute("items", products);
		
		return "itemsList";
	}
	
	@GetMapping("addItem")
	public String showAddItemForm(Model model)
	{
		model.addAttribute("itemForm", new ItemForm());
		
		return "addItem";
	}
	
	@PostMapping("addItem")
	public String addNewItem(@ModelAttribute("itemForm") ItemForm itemForm, Model model)
	{
		String name = itemForm.getName();
		String description = itemForm.getDescription();
		
		if (null == name || name.length() <= 0
				|| null == description || description.length() <= 0)
		{
			model.addAttribute("errorMessage", errorMessage);
		}
		else
		{
			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			
			product = repository.save(product);
			
			log.info("-----> saved product with name: " + product.getName());
			
			model.addAttribute("successMessage", successMessage);
		}
		
		return "addItem";
		
	}
}
