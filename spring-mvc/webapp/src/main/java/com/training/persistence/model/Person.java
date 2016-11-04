package com.training.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

  @Column(name = "person_id")
  long id;

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

  @OneToOne(optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "id")
  @Column(name = "user_id")
  long userId;
}
