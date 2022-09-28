package com.reneei.TvShowsBeltExam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.reneei.TvShowsBeltExam.models.LoginUser;
import com.reneei.TvShowsBeltExam.models.Show;
import com.reneei.TvShowsBeltExam.models.User;
import com.reneei.TvShowsBeltExam.services.ShowService;
import com.reneei.TvShowsBeltExam.services.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShowService showService;
	
	@GetMapping("/")
	public String index(
		@ModelAttribute("newUser") User user,
		@ModelAttribute("newLogin") LoginUser loginUser) {
		return "index.jsp";
	}

// Registering a user
	@PostMapping("/register")
	public String addUser(@Valid
		@ModelAttribute("newUser") User user,
		BindingResult outcome,
		HttpSession session,
		@ModelAttribute("newLogin") LoginUser loginUser) {
		
		// validate user
		userService.validate(user, outcome);
		if(outcome.hasErrors()) {
			return "index.jsp";
		}
		// Register user
		userService.addUser(user);
		// place the current user in session
		session.setAttribute("userLoggedIn", user);
			return "redirect:/shows";
		}
	
// Logging in
	@PostMapping("/login")
	public String loginUser(@Valid
		@ModelAttribute("newLogin") LoginUser loginUser,
		BindingResult outcome,
		HttpSession session,
		@ModelAttribute("newUser") User user) {
		
		// Authenticate user
		userService.authUser(loginUser, outcome);
		if(outcome.hasErrors()) {
			return "index.jsp";
		}
		User userLoggedIn=userService.findByEmail(loginUser.getEmail());
		// put user in session
		session.setAttribute("userLoggedIn", userLoggedIn);
		return "redirect:/shows";
	}

// logging out
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
// Below is the start of the routes for the tv show content

	@GetMapping("/shows")
	public String dashboard(HttpSession session, Model model) {
		// check if user is in session
		if(session.getAttribute("userLoggedIn")!=null) {
			// if user is in session then going to bring in info from the model
			model.addAttribute("allShows", showService.allShows());
			return "dashboard.jsp";
		} else {
			return "redirect:/";
		}
	}
	
// Adding a new show
	@GetMapping("/shows/new")
	public String addShow(HttpSession session, @ModelAttribute("addShow") Show show) {
		// always check if user is in session
		if(session.getAttribute("userLoggedIn")!=null) {
			return "add.jsp";
		} else {
			return "redirect:/";
		}
	}
	
// Creating a new show and adding it to the db
	@PostMapping("/shows/add")
	public String addToDb(@Valid @ModelAttribute("addShow") Show show, BindingResult outcome) {
		if(outcome.hasErrors()) {
			return "add.jsp";
		}
		else {
			showService.addShow(show);
			return "redirect:/shows";
		}
	}
	
// Getting details on one show
	@GetMapping("/shows/{id}")
	public String showDetails(@PathVariable Long id, Model model, HttpSession session) {
		if(session.getAttribute("userLoggedIn")!=null) {
		Show show=showService.getOneShow(id);
		model.addAttribute("show",show);
		return "details.jsp";
		}
		else {
			return "redirect:/";
		}
	}
	
	// Deleting a show with DeleteMapping
		@DeleteMapping("/delete/{id}")
		public String deleteShow(@PathVariable Long id) {
			showService.deleteShow(id);
			return "redirect:/shows";
		}
		
		// Getting the info to display on the edit form
		@GetMapping("/edit/{id}")
		public String editShow(@PathVariable("id") Long id, Show show, Model model) {
			model.addAttribute("show", showService.getOneShow(id));
			return "edit.jsp";
		}

		// Editing show information
		@PutMapping("/edit/{id}")
		public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("show") Show show, BindingResult outcome) {
			if (outcome.hasErrors()) {
				return "redirect:/edit/{id}";
			}
			else {
				showService.updateShow(show);
				return "redirect:/shows";
			}
		}
	
}
