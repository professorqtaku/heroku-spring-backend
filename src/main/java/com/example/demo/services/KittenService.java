package com.example.demo.services;

import com.example.demo.Utilities;
import com.example.demo.entities.Kitten;
import com.example.demo.entities.User;
import com.example.demo.repositories.KittenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqlite.SQLiteException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// a service is where we write our logic for the controller
@Service
public class KittenService {
  
  @Autowired
  private KittenRepository kittenRepository;
  
  // will run when this object is created,
  // kinda like useEffect or Mounted
//  @PostConstruct
//  public void init() {
//    Kitten cocos = getById(4).get();
//
//    User johan = User.builder()
//        .id(5)
//        .build();
//
//    cocos.setOwner(johan);
//
//    // only requires a user with an id
//    // to connect the relation
//    saveKitten(cocos);
//  }
  
  public List<Kitten> getAll() {
    return kittenRepository.findAll();
  }
  
  public Optional<Kitten> getById(long id) {
    return kittenRepository.findById(id);
  }
  
  public Kitten saveKitten(Kitten kitten) {
    try {
      
      // save will create a new row in the table
      // or update existing if matching id
      return kittenRepository.save(kitten);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public List<Kitten> getByColor(String color) {
    return kittenRepository.findAllByColorIgnoreCase(color);
  }
  
  public void deleteById(long id) {
    kittenRepository.deleteById(id);
  }
  
  public Kitten updateById(long id, Map values) {
    Optional<Kitten> kittenOptional = getById(id);
    
    if(kittenOptional.isPresent()) {
      var kitten = kittenOptional.get();
      
      // update only the values in the kitten object
      // that matches the values from the hashMap
  
      // helper method to set private variables in an object
      Utilities.updatePrivateFields(kitten, values);
      
      return kittenRepository.save(kitten);
    }
    
    return null;
  }
}
