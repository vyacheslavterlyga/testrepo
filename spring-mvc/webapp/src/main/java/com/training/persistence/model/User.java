package com.training.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "userSeqGen", sequenceName = "AUSER_SEQUENCE")
@Table(name = "AUSER")
public class User extends AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
  @Column(name = "id")
  Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "role")
  private String role;

  @Column(name = "start_date")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date startDate;

  @Column(name = "end_date")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private Date endDate;

  @OneToOne(mappedBy = "user")
  private Person person;

}
