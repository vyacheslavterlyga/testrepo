package com.training.persistence.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @Generated(GenerationTime.NEVER)
  @Column(name = "id")
  @Getter
  @Setter
  Long id;

}
