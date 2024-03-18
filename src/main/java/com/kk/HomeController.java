package com.kk;


import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping("/")
	public String  displayOrderDetails(Model model) {
		String caption = "";
		LocalTime currentTime = LocalTime.now();

        if (currentTime.isBefore(LocalTime.NOON)) {
        	caption = "Good morning";
        } else {
        	caption = "Good afternoon";
        }

        caption =  caption + ", Khushboo Koshti, Welcome to COMP367";
        model.addAttribute("caption", caption);

        return "index";
	}
}
