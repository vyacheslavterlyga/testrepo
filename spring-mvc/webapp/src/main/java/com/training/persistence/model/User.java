package com.training.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUSER")
public class User extends AbstractEntity {

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
}
