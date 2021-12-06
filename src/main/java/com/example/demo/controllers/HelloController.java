package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*      method
     C - POST   - @PostMapping
     R - GET    - @GetMapping
     U - PUT    - @PutMapping
     D - DELETE - @DeleteMapping
 */

// annotate each controller to enable routing of endpoints
@RestController
public class HelloController {
  
  @GetMapping("/greet")
  public String greeting() {
    return "Greetings you gorgeous people";
  }
  
  // try dynamic url parameters
  @GetMapping("/params/{name}/{age}")
  public String testParams(@PathVariable String name, @PathVariable int age) {
    return String.format("Hello %s, who is %d years old", name, age);
  }
  
  // try dynamic url queries
  // localhost:8080/queries?name=Johan&age=32
  @GetMapping("/queries")
  public String testQueries(@RequestParam String name, @RequestParam int age) {
    return String.format("Query! Hello %s, who is %d years old", name, age);
  }
}
