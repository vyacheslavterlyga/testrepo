package com.training.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractEntity {

  @Id
  @Column(name = "id")
  String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
