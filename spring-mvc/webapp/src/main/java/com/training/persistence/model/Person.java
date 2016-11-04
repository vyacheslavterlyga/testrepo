package com.training.persistence.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

  @Column(name = "first_name")
  @Getter
  @Setter
  private String firstName;

  @Column(name = "last_name")
  @Getter
  @Setter
  private String lastName;

  @Column(name = "age")
  @Getter
  @Setter
  private Integer age;

  @Column(name = "start_date")
  @Getter
  @Setter
  private Date startDate;

  @Column(name = "end_date")
  @Getter
  @Setter
  private Date endDate;

  @Getter
  @Setter
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

}
