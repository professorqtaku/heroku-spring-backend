package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  
  @Id // Primary Key
  @GeneratedValue // Auto increment
  private long id;
  
  private String name;
  private String username;
  private String password;
  private int age;
  
  // connect relation to the owner field in the Kitten entity
  @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER) // EAGER will always fetch related entity
  @JsonIgnoreProperties({"owner"}) // prevent circular reference between relations with this property
  private List<Kitten> pets;
  
  // friends list relates users to users
  // and relates them with a cross table
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH) // DETACH || REMOVE
  @JoinTable(
      name = "friends_list", // the cross table
      joinColumns = @JoinColumn(name = "user_id"), // the column for this user
      inverseJoinColumns = @JoinColumn(name = "friend_id") // the column for the friend
  )
//  @JsonIgnoreProperties({"friends", "pets"}) // prevent circular reference
  @JsonIncludeProperties({"id", "name"})
  private List<User> friends;
  
  public void addKitten(Kitten kitten) {
    pets.add(kitten);
  }
  
  public void addFriend(User friend) {
    friends.add(friend);
  }
  
  // prevent leaking password to frontend
  @JsonIgnore
  public String getPassword() {
    return password;
  }
  
  // enable password from frontend when logging in
  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }
}
