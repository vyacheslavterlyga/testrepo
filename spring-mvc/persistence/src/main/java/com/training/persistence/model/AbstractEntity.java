package com.training.persistence.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @GeneratedValue(generator = "generator")
  @GenericGenerator(name = "generator", strategy = "org.hibernate.id.IdentityGenerator")
  @Column(name = "id")
  Long id;

}
