package com.biblioteca.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	//login/register
    @GetMapping("/")
	public String index() {
		return "index";
	}
}
