package com.training.persistence.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

  @Column(name = "first_name")
  @Getter
  @Setter
  String firstName;

  @Column(name = "last_name")
  @Getter
  @Setter
  String lastName;

  @Column(name = "age")
  @Getter
  @Setter
  int age;

  @Column(name = "start_date")
  @Getter
  @Setter
  Date startDate;

  @Column(name = "end_date")
  @Getter
  @Setter
  Date endDate;

}
