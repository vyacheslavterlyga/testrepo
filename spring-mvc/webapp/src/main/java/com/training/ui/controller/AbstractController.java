package com.training.ui.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class AbstractController {

  private static final String DATE_PATTERN = "datePattern";

  @Autowired
  private MessageSource messageSource;

  @InitBinder
  protected void initBinder(ServletRequestDataBinder binder) throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat(messageSource.getMessage(DATE_PATTERN, null, null));
    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, editor);
  }

}
