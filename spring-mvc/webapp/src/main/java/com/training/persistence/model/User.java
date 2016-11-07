package com.training.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUSER")
public class User extends AbstractEntity {

  @Column(name = "login")
  @Getter
  @Setter
  private String login;

  @Column(name = "password")
  @Getter
  @Setter
  private String password;

  @Column(name = "role")
  @Getter
  @Setter
  private String role;

  @Column(name = "start_date")
  @Getter
  @Setter
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date startDate;

  @Column(name = "end_date")
  @Getter
  @Setter
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date endDate;

  @Getter
  @Setter
  @OneToOne(mappedBy = "user")
  private Person person;

}
