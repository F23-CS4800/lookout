package com.example.crimes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @GetMapping("/home")
	public String getHome() {
		// model.addAttribute("name", name);
		return "index";
	}
	@GetMapping("/map")
	public String getMap() {
		// model.addAttribute("name", name);
		return "map";
	}
}

