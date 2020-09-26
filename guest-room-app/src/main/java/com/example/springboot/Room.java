package com.example.springboot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room{	
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)	
	public Long		id;
	public String	name;
	public String	address;
	public String	floorSize;
	public Integer	numberOfBeds;
	public Integer	maximumDays;
	public Double	amountPerDay;
	public Long		housOwnerId;

	public Room(){

	}

	public String toString(){
		return this.name+"-"+this.address+"-"+this.floorSize+"-"+this.numberOfBeds+"-"+this.maximumDays+"-"+this.amountPerDay+"-"+this.housOwnerId;
	}
	public Room(String name, String address, String floorSize, Integer numberOfBeds, Integer maximumDays, Double amountPerDay, Long housOwnerId){
		this.name = name;
		this.address = address;
		this.floorSize = floorSize;
		this.numberOfBeds = numberOfBeds;
		this.maximumDays = maximumDays;
		this.amountPerDay = amountPerDay;
		this.housOwnerId = housOwnerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getFloorSize() {
		return floorSize;
	}

	public void setFloorSize(String floorSize) {
		this.floorSize = floorSize;
	}
	
	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}

	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	public Integer getMaximumDays() {
		return maximumDays;
	}

	public void setMaximumDays(Integer maximumDays) {
		this.maximumDays = maximumDays;
	}

	public Double getAmountPerDay() {
		return amountPerDay;
	}

	public void setAmountPerDay(Double amountPerDay) {
		this.amountPerDay = amountPerDay;
	}
	
	public Long getHousOwnerId() {
		return housOwnerId;
	}

	public void setHousOwnerId(Long housOwnerId) {
		this.housOwnerId = housOwnerId;
	}

}