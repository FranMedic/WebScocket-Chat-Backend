package com.spring.the.second.chat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.the.second.chat.models.documents.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje,String> {
	
	public List<Mensaje>findFirst10ByOrderByFechaDesc();

}
