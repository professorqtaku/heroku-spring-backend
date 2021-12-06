package com.example.demo.entities;

// lombok docs: https://projectlombok.org/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
@Builder will create builder functionality with this class.

Example:
Person person = Person.builder()
  .name("Adam Savage")
  .city("San Francisco")
  .job("Mythbusters")
  .job("Unchained Reaction")
  .build();

 */

// tell hibernate that this class will be used in the database
// in the table called 'kittens'
@Entity
@Table(name = "kittens")
@Data // will generate default getters and setters for all values
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kitten {
  
  @Id // Primary Key
  @GeneratedValue // Auto increment
  private long id;
  
  private String name;
  private int age;
  private String color;
  private String favoriteToy;
  
  // tell Spring that this entity relates to an User
  @ManyToOne
  @JsonIgnoreProperties({"pets"})
  private User owner;

  // generate getter and setters
}
