package com.training.persistence.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUSER")
public class User extends AbstractEntity {

  @OneToOne(optional = false)
  @Column(name = "login")
  @Getter
  @Setter
  String login;

  @Column(name = "password")
  @Getter
  @Setter
  String password;

  @Column(name = "role")
  @Getter
  @Setter
  String role;

  @Column(name = "start_date")
  @Getter
  @Setter
  Date startDate;

  @Column(name = "end_date")
  @Getter
  @Setter
  Date endDate;
}
