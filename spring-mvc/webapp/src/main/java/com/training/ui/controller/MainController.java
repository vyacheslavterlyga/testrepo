package com.training.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	UserDAO m_UserDao;
	
	@RequestMapping(value = "/index")
	public ModelAndView startMethod(){
		logger.debug("open index page");
		User user = m_UserDao.getUserById("root");
		return new ModelAndView("index", "User", user);
	}
}
