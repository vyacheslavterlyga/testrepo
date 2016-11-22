package com.training.ui.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.training.service.user.Role;
import com.training.service.user.User;
import com.training.service.user.UserServicePortType;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController extends AbstractController {

  @Autowired
  UserServicePortType userService;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public ModelAndView homePage() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    log.debug("open index page for user '{}'", userDetails.getUsername());
    User user = userService.getByLogin(userDetails.getUsername());
    return new ModelAndView("index", "User", user);
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public ModelAndView addUser(@ModelAttribute("User") User user) {
    log.debug("open add User form page");
    return new ModelAndView("addUserForm", "User", user);
  }

  @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
  public String saveNewUser(@ModelAttribute("User") User user, final RedirectAttributes redirectAttributes, BindingResult bindingResult) {
    log.debug("open add User page; start insert User in DB");
    if (userService.getByLogin(user.getLogin()) != null) {
      redirectAttributes.addFlashAttribute("User", user);
      redirectAttributes.addFlashAttribute("errorMessage", "User with this login already exists in DB");
      return "redirect:/user/add";
    }
    userService.add(user);
    log.debug("User saved id:'{}'", user.getId());
    return "redirect:/user/index";
  }

  @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
  public String addOrUpdateUser(
      @ModelAttribute("User") User user,
      final RedirectAttributes redirectAttributes,
      BindingResult bindingResult) {
    log.debug("Open add-or-update User page. Start inserting info into DB");
    if (userService.getByLogin(user.getLogin()) != null) {
      redirectAttributes.addFlashAttribute("User", user);
      redirectAttributes.addFlashAttribute("errorMessage", "User with this login already exists in DB");
      return "redirect:/user/add";
    }
    userService.add(user);
    log.debug("User upated or saved with ID:'{}'", user.getId());
    return "redirect:/user/allUsersList";
  }

  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public ModelAndView updateUserForm(@RequestParam("userId") Long id) throws AccessException {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userById = userService.getById(id);
    User userFromSessionSeq = userService.getByLogin(userDetails.getUsername());

    if (( !userById.getLogin().equals(userDetails.getUsername()) && userFromSessionSeq.getRole() != Role.ADMIN) ||
      userFromSessionSeq.getRole() == Role.GUEST) {
      throw new AccessException("You do not have access to this page");
    }
    User user = userService.getById(id);
    return new ModelAndView("updateUserForm", "User", user);
  }

  @RequestMapping(value = "/saveUpdateUser", method = RequestMethod.POST)
  public String saveUpdateUser(@ModelAttribute("User") User user) {
    log.debug("start update User in DB");
    userService.update(user);
    log.debug("User updated id:'{}'", user.getId());
    return "redirect:/user/index";
  }

  @RequestMapping(value = "/validateLogin")
  public @ResponseBody String validateLogin(@RequestParam("login") String login) {
    if (userService.getByLogin(login) != null) {
      return "false";
    }
    return "true";
  }

  @RequestMapping(value = "/allUsersList", method = RequestMethod.GET)
  public ModelAndView allUserView() throws JsonGenerationException, JsonMappingException, IOException {
    log.debug("open page for view all users");
    List<User> userList = (List<User>) userService.getByLimit(10, 15);
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByLogin(userDetails.getUsername());
    ObjectMapper objectMapper = new ObjectMapper();

    ModelAndView model = new ModelAndView("allUsersList");
    model.addObject("lists", userList);
    model.addObject("UserRole", user.getRole());
    model.addObject("UserLogin", user.getLogin());
    //model.addObject("userListJson", objectMapper.writeValueAsString(userService.getAll()));
    return model;
  }

  @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
  public String deleteCurrentUser(@RequestParam("Id") Long id) {
    log.debug("start deleting User from database");
    User user = userService.getById(id);
    userService.delete(user);
    log.debug("user " + user.getLogin() + " deleted successfully");
    return "redirect:/user/allUsersList";
  }
}
