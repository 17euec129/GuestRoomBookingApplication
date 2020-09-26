package com.example.springboot;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
// This will be AUTO IMPLEMENTED by Spring into a Bean called roomRepository
// CRUD refers Create, Read, Update, Delete

public interface RoomRepository extends CrudRepository<Room, Long> {
	 List<Room> findAll();
	 Room getById(Long id);	
}