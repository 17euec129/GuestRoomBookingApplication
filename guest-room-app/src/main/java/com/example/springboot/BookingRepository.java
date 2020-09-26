package com.example.springboot;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
// This will be AUTO IMPLEMENTED by Spring into a Bean called accountRepository
// CRUD refers Create, Read, Update, Delete

public interface BookingRepository extends CrudRepository<Booking, Long> {
	 List<Booking> findAll();
	 List<Booking> findAllByRoomId(Long roomId);
}