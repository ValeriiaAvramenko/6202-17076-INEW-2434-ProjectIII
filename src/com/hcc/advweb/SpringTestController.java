package com.hcc.advweb;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class SpringTestController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewStudentDetails(ModelMap model) { 
	    return "authentication";
	}
	

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ModelAndView authorize(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		
		boolean result = UserListDB.authentification(username, password);
		
		if(result) {
			return new ModelAndView("greeting","message","Authorization successful");
			}
		else {
			return new ModelAndView("authentication","message","Authorization failed");
		}
	    
	}
	
	
}
