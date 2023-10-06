package com.biblioteca.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.login.model.LoginModel;
import com.biblioteca.model.LibEmployee;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("libEmployee", new LibEmployee());
		return "login/login";
	}

	@PostMapping("/login") 
	public String loginSubmit(@ModelAttribute LibEmployee libEmployee , Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("libEmployee", libEmployee);

		LoginModel loginModel = new LoginModel(libEmployee);

	
        if(!(loginModel.hasUser() && loginModel.passwordEquals())) {
			return "login/login";
		}

		libEmployee = loginModel.getEmployee();
		redirectAttributes.addFlashAttribute("libEmployee", libEmployee);

		String cargo = libEmployee.getCargo();

		if(cargo.equals("A")) {
			return "redirect:bibliotecaria";
		}

		return "redirect:bibliotecaria";
	}
}
