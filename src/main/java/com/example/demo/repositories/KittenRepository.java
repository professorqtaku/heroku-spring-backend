package com.example.demo.repositories;

import com.example.demo.entities.Kitten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// creates a means to communicate with the 'kittens' table in the database
@Repository
public interface KittenRepository extends JpaRepository<Kitten, Long> {
  
  // SELECT * FROM kittens WHERE LOWER(color) = LOWER('Orange')
  
  List<Kitten> findAllByColorIgnoreCase(String color);
  
  @Query(value = "SELECT * FROM kittens WHERE LOWER(color) = LOWER(:color)", nativeQuery = true)
  List<Kitten> customFindAllByColorIgnoreCase(@Param("color") String color);
  
  @Query(value = "SELECT * FROM kittens WHERE name = :name", nativeQuery = true)
  List<Kitten> customQueryToGetCatsByName(@Param("name") String name);
}
