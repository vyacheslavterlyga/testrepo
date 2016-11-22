package com.training.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PERSON")
public class Person extends AbstractEntity {

  @Id
  @GeneratedValue(generator = "personGenerator")
  @GenericGenerator(name = "personGenerator", strategy = "org.hibernate.id.IdentityGenerator")
  @Column(name = "id")
  Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @OneToOne
  @JoinColumn(name = "user_id")
  @JsonBackReference
  private User user;

}
