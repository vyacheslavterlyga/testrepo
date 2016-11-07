package com.training.ui.controller;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {

  @Autowired
  UserDAO userDao;

  @RequestMapping(value = "/list")
  public ModelAndView list() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.debug("open index page for user '{}'", userDetails.getUsername());
    User user = userDao.getByLogin(userDetails.getUsername());
    return new ModelAndView("index", "User", user);
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView addUser() {
    log.debug("open add User form page");
    return new ModelAndView("addUserForm", "User", new User());
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ModelAndView saveUser(@ModelAttribute("User") User user) {
    log.debug("open add User page; start insert User in DB");
    userDao.add(user);
    log.debug("User saved id:'{}'", user.getId());
    return list();
  }
}