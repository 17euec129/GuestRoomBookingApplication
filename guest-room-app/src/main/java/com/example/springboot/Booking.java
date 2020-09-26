package com.example.springboot;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Booking{
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)	
	public Long	id;
	public Long	guestId;
	public Long	roomId;
	@Temporal(TemporalType.DATE)
	public Date	startDateOfBooking;
	@Temporal(TemporalType.DATE)
	public Date	endDateOfBooking;
	public Double billToPay;

	public Booking(){

	}

	public Booking(Long guestId, Long roomId, Date startDateOfBooking, Date endDateOfBooking, Double billToPay){
		this.guestId = guestId;
		this.roomId = roomId;
		this.startDateOfBooking = startDateOfBooking;
		this.endDateOfBooking = endDateOfBooking;
		this.billToPay = billToPay;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
	
	public Date getStartDateOfBooking() {
		return startDateOfBooking;
	}

	public void setStartDateOfBooking(Date startDateOfBooking) {
		this.startDateOfBooking = startDateOfBooking;
	}
	
	public Date getEndDateOfBooking() {
		return endDateOfBooking;
	}

	public void setEndDateOfBooking(Date endDateOfBooking) {
		this.endDateOfBooking = endDateOfBooking;
	}
	
	public Double getBillToPay() {
		return billToPay;
	}

	public void setBillToPay(Double billToPay) {
		this.billToPay = billToPay;
	}
	
}