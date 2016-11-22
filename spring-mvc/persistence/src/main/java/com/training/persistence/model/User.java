package com.training.persistence.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AUSER")
public class User extends AbstractLifeCycleEntity {

  public static enum ROLE {
      ADMIN,
      USER,
      GUEST
  }

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  @Enumerated(value = EnumType.STRING)
  private ROLE role;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Person person;

}
