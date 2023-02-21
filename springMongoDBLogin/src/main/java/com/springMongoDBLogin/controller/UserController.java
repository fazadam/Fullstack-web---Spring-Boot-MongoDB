package com.springMongoDBLogin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.repository.UserRepository;

@Controller
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/")
	public String homePage() {
		return "ez a homeOldal";
	}
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("ez a homeoldal");
	    return modelAndView;
	}
	
	@GetMapping("/signup")
	public String registration(Model model) {
		model.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping(value = "/signup",method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute User user) {
		System.out.println("uj user regisztralva");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		return "/login";
		
	}
}
