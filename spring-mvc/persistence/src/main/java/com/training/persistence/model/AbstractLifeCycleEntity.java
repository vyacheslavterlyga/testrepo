package com.training.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractLifeCycleEntity extends AbstractEntity {

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

}
