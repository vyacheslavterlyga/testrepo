package com.training.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

  @OneToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "login")
  @Column(name = "person_id")
  int id;

  @Column(name = "first_name")
  String firstName;

  @Column(name = "last_name")
  String lastName;

  @Column(name = "age")
  int age;
}
