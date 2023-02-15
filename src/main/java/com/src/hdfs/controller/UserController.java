package com.src.hdfs.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.src.hdfs.config.auth.PrincipalDetails;
import com.src.hdfs.model.User;
import com.src.hdfs.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptpasswordencoder; 
	
	@GetMapping({"/",""})
	public String loginFail(Model model) {
		return "/loginForm";
	}
	
	@GetMapping("/user")
	public String login(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("name", name);
		return "/main";
	}
	@GetMapping("/main")
	public String goMain(Authentication authentication,
		@AuthenticationPrincipal PrincipalDetails userDetails) {
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		return "/main";
	}
	
	@GetMapping("loginForm")
	public String loginChk(@RequestParam(value="error", required = false)String error,
			 				@RequestParam(value="exception", required =false)String exception,
			 				Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "loginForm";
	}
	
	@PostMapping("/join")
	public String goJoin(User user, Model model) {
		
		try {
			if(chk (user.getUsername())) {
				model.addAttribute("status", "YY");
			}else {
				user.setRole("USER_ROLE");
				String email = user.getUsername();
				String password = user.getPassword();
				String enc = bcryptpasswordencoder.encode(password);
				user.setUsername(email);
				user.setPassword(enc);
				
				userRepository.save(user);
				model.addAttribute("status", "Y");
			}
		}catch (Exception e) {
			
		}
		
		return "loginForm";
	}
	
	public boolean chk (String username) {
		User userChk = userRepository.findByUsername(username);
		if (userChk != null) {
			return true;
		}else {
			return false;
		}
		
	}

}
