package com.training.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.util.StringUtils;

public class XMLGregorianCalendarEditor extends PropertyEditorSupport {

  private final DateFormat dateFormat;

  private final boolean allowEmpty;

  private final int exactDateLength;

  public XMLGregorianCalendarEditor(DateFormat dateFormat, boolean allowEmpty) {
    this.dateFormat = dateFormat;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = -1;
  }

  public XMLGregorianCalendarEditor(DateFormat dateFormat, boolean allowEmpty, int exactDateLength) {
    this.dateFormat = dateFormat;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = exactDateLength;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (this.allowEmpty && !StringUtils.hasText(text)) {
      // Treat empty String as null value.
      setValue(null);
    } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
      throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
    } else {
      try {
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(this.dateFormat.parse(text));
        XMLGregorianCalendar XMLDate;
        try {
          XMLDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
          setValue(XMLDate);
        } catch (DatatypeConfigurationException e) {
          e.printStackTrace();
        }
      } catch (ParseException ex) {
        throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
      }
    }
  }

  @Override
  public String getAsText() {
    XMLGregorianCalendar value = (XMLGregorianCalendar) getValue();
    return (value != null ? this.dateFormat.format(value.toGregorianCalendar().getTime()) : "");
  }
}
