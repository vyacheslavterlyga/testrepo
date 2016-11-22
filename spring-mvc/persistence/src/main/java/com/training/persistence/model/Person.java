package com.training.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PERSON")
public class Person extends AbstractLifeCycleEntity {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "birthday")
  private Date birthday;

  @OneToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

}
