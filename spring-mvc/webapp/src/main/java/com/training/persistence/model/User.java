package com.training.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUSER")
public class User extends AbstractEntity {

  @Column(name = "password")
  String m_Password;

  @Column(name = "name")
  String m_Name;

  @Column(name = "role")
  String m_Role;

  public String getPassword() {
    return m_Password;
  }

  public void setPassword(String password) {
    m_Password = password;
  }

  public String getName() {
    return m_Name;
  }

  public void setName(String name) {
    m_Name = name;
  }

  public String getRole() {
    return m_Role;
  }

  public void setRole(String role) {
    m_Role = role;
  }
}
