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

  @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET, params = "userId")
  public ModelAndView addOrUpdateUser(@RequestParam("userId") Long userId) throws AccessException {
    log.debug("Open add-or-update User page");

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    ModelAndView modelAndView = new ModelAndView("addOrUpdateUser");
    if (userId == null) {
      if (getCurrentUser().getRole() != Role.ADMIN) {
        throw new AccessException("You are not admin!!!");
      } else {
        log.debug("Start inserting info into DB");
        modelAndView.addObject("User", new User());
      }
    } else {
      if (( !getCurrentUser().getLogin().equals(userService.getById(userId).getLogin()) && getCurrentUser().getRole() == Role.USER) ||
        getCurrentUser().getRole() == Role.GUEST) {
        throw new AccessException("You have not access on this page!!!");
      } else {
        log.debug("Start updating info in DB");
        modelAndView.addObject("User", userService.getById(userId));
      }
    }
    return modelAndView;
  }

  @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.GET)
  public ModelAndView addOrUpdateUser() throws AccessException {
    return addOrUpdateUser(null);
  }

  @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
  public ModelAndView saveUser(@ModelAttribute("User") User user, final RedirectAttributes redirectAttributes) throws Exception {
    if (user.getId() == null) {
      log.debug("Start add new user");
      if (userService.getByLogin(user.getLogin()) != null) {
        throw new Exception("User with this login already is in DB!!!");
      } else {
        userService.add(user);
      }
    } else {
      if (userService.getById(user.getId()) == null) {
        throw new Exception("This user are not in DB!!!");
      } else {
        log.debug("Start update user");
        userService.update(user);
      }
    }
    return homePage();
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
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByLogin(userDetails.getUsername());
    ObjectMapper objectMapper = new ObjectMapper();

    ModelAndView model = new ModelAndView("allUsersList");
    model.addObject("UserRole", user.getRole());
    model.addObject("UserLogin", user.getLogin());
    model.addObject("userListJson", objectMapper.writeValueAsString(userService.getByLimit(5, 7, "id", true)));
    model.addObject("Count", userService.getCount());
    return model;
  }

  @RequestMapping(value = "/getAllUsersTable", method = RequestMethod.GET)
  private @ResponseBody ModelAndView showTable(
      @RequestParam("firstRow") int firstRow,
      @RequestParam("countRows") int countRows,
      @RequestParam("orderBy") String orderBy,
      @RequestParam("asc") Boolean asc) {
    ModelAndView model = new ModelAndView("allUsersTable");
    List<User> userList = userService.getByLimit(firstRow, countRows, orderBy, asc);

    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByLogin(userDetails.getUsername());
    model.addObject("lists", userList);
    model.addObject("UserRole", user.getRole());
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

  private User getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userService.getByLogin(userDetails.getUsername());
    return user;
  }
}
