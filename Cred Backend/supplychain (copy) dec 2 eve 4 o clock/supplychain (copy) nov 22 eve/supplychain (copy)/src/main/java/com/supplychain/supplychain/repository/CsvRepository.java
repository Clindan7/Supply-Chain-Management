package com.supplychain.supplychain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.supplychain.supplychain.entity.Item;

public interface CsvRepository  extends JpaRepository<Item, Integer>{
    
}
