package com.training.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WelcomeController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String welcome() {
    log.debug("open welcom page");
    return "sp";
  }
}
