package com.caglar.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArabaRepository extends JpaRepository<Araba,Long> {

public List<Araba> getAllByMarkaAdEquals(String marka);

}
