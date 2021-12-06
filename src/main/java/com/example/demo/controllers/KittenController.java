package com.example.demo.controllers;

import com.example.demo.entities.Kitten;
import com.example.demo.repositories.KittenRepository;
import com.example.demo.services.KittenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest") // prefix all mappings with this value
public class KittenController {
  
  // a controller will never access a repository directly,
  // it does that through a service
  // generates a setter and sets the value
  @Autowired
  private KittenService kittenService;
  
  // ResponseEntity is used to set status code on returned response
  @GetMapping("/kittens")
  public ResponseEntity<List<Kitten>> getAllKittens(@RequestParam(required = false) String color) {
    // the repository has a bunch of methods for accessing the database

    // /rest/kittens?color=Orange
    if (color != null) {
      List<Kitten> kittens = kittenService.getByColor(color);
      if(kittens.size() > 0) {
        // all good, return status 200
        return ResponseEntity.ok(kittens);
      } else {
        // no kittens found, return status code 204 for no content
        return ResponseEntity.noContent().build();
      }
    }
  
    return ResponseEntity.ok(kittenService.getAll());
  }
  
  @GetMapping("/kittens/{id}")
  public ResponseEntity<Optional<Kitten>> getKittenById(@PathVariable long id) {
    
    // the Spring convention is to use the Optional
    // for handling edge cases like if the response is null
    Optional<Kitten> kitten = kittenService.getById(id);
    if(kitten.isPresent()) {
      return ResponseEntity.ok(kitten);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
  
  @PostMapping("/kittens")
  public ResponseEntity<Kitten> createKitten(@RequestBody Kitten kitten) {
    Kitten saved = kittenService.saveKitten(kitten);
    if(saved != null) {
      return ResponseEntity.ok(kitten);
    } else {
      // if entity couldn't be saved to database
      // send status code 400 (Bad Request)
      return ResponseEntity.badRequest().build();
    }
  }
  
  // should this endpoint send status 200 (OK) if the id doesn't exist?
  @PutMapping("/kittens/{id}")
  public Kitten updateKitten(@PathVariable long id, @RequestBody Map values) {
    return kittenService.updateById(id, values);
  }
  
}
