package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.UserDAO;
import com.model.User;

@Controller
public class MainController {
	
	@Autowired
	UserDAO m_UserDao;
	
	@RequestMapping(value = "/index")
	public ModelAndView startMethod(){
		User user = m_UserDao.getUserById("root");
		return new ModelAndView("index", "User", user);
	}
}
