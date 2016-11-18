package com.training.ui.controller;

import java.text.SimpleDateFormat;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.training.propertyeditors.XMLGregorianCalendarEditor;

public class AbstractController {

  private static final String DATE_PATTERN = "datePattern";

  @Autowired
  private MessageSource messageSource;

  @InitBinder
  protected void initBinder(ServletRequestDataBinder binder) throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat(messageSource.getMessage(DATE_PATTERN, null, LocaleContextHolder.getLocale()));
    XMLGregorianCalendarEditor editor = new XMLGregorianCalendarEditor(dateFormat, true);
    binder.registerCustomEditor(XMLGregorianCalendar.class, editor);
  }

}
