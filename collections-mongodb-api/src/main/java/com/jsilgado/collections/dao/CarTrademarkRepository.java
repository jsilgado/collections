package com.jsilgado.collections.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jsilgado.collections.domain.CarTrademark;

@Repository
public interface CarTrademarkRepository extends MongoRepository<CarTrademark, String> {

}
