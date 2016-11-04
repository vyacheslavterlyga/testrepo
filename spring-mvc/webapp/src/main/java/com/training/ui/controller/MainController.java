package com.training.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.training.persistence.dao.UserDAO;
import com.training.persistence.model.User;

@Controller
public class MainController {

  private static final Logger logger = LoggerFactory.getLogger(MainController.class);

  @Autowired
  UserDAO userDao;

  @RequestMapping(value = "/index")
  public ModelAndView startMethod() {
    logger.debug("open index page");

    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal != null && UserDetails.class.isAssignableFrom(principal.getClass())) {
      UserDetails securityUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user = userDao.getByLogin(securityUser.getUsername());
      return new ModelAndView("index", "User", user);
    }

    return new ModelAndView("sp");
  }

  @RequestMapping(value = "/addUser", method = RequestMethod.GET)
  public ModelAndView addUserForm() {
    logger.debug("open add User form page");
    return new ModelAndView("addUserForm", "User", new User());
  }

  @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
  public ModelAndView saveUser(@ModelAttribute("User") User user) {
    logger.debug("open add User page; start insert User in DB");
    userDao.add(user);
    return new ModelAndView("forward:/index");
  }
}
