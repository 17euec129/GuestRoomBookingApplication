package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.text.SimpleDateFormat;


@RestController
public class HelloController {
	@Autowired 
	private AccountRepository accountRepository;
	@Autowired 
	private RoomRepository roomRepository;
	@Autowired 
	private BookingRepository bookingRepository;

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("/register")
	@ResponseBody
	public String register(@RequestBody Account account){
		if(!(account.userType.equals("HouseOwner") || account.userType.equals("Customer"))){
			return "USER TYPE NOT EXSISTS";
		}else if(accountRepository.findAllByUsername(account.username).size() > 0){
			return "USERNAME ALREADY EXSISTS";
		}else if(accountRepository.findAllByEmailId(account.emailId).size() > 0){
			return "EMAILID ALREADY EXSISTS";
		}else{
			try{
				accountRepository.save(account);
				return "ACCOUNT CREATED SUCCESSFULLY";
			}
			catch(Exception e){
				return "INVALID INPUT DATA";
			}
		}
	}

	@GetMapping("/login")
	@ResponseBody
	public String login(String username, String password, HttpServletRequest request){
		List<Account> accList = accountRepository.findAllByUsernameAndPassword(username,password);
		if(accList.size() > 0){
			request.getSession().setAttribute("sessionId", (accList.get(0).getId()).toString());
			return "LOGGED IN SUCCESSFULLY";			
		}else{
			return "INVALID USERNAME/PASSWORD";
		}
	}

	@GetMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "LOGGED OUT SUCCESSFULLY";
	}

	public Account getSessionUser(HttpServletRequest request){
		Object sessionIdObj = request.getSession().getAttribute("sessionId");
		if(sessionIdObj == null){
			return null;
		}
		else{
			String id = sessionIdObj.toString();
			return accountRepository.getById(Long.parseLong(id));
		}
	}

	@PostMapping("/addRoom")
	@ResponseBody
	public String addRoom(@RequestBody Room room, HttpServletRequest request){
		Account owner = this.getSessionUser(request);
		if(owner == null){
			return "KINDLY LOGIN TO ACCESS THIS API";
		}else if(!owner.userType.equals("HouseOwner")){
			return "ONLY OWNER CAN ADD ROOMS";
		}else{
			try{
				room.setHousOwnerId(owner.getId());
				roomRepository.save(room);
				return "ROOM CREATED SUCCESSFULLY";
			}
			catch(Exception e){
				e.printStackTrace();
				return "INVALID INPUT DATA";
			}
		}
	}

	@GetMapping("/room/{id}")
	@ResponseBody
	public Object getRoom(@PathVariable(value="id") String id, HttpServletRequest request){
		Account user = this.getSessionUser(request);
		if(user == null){
			return "KINDLY LOGIN TO ACCESS THIS API";
		}else{
			Room room = roomRepository.getById(Long.parseLong(id));
			if(room == null){
				return "ROOM NOT FOUND, TRY TO FETCH ALL ROOMS BY /room URL";
			}else{
				return room;
			}
		}
	}

	@GetMapping("/room")
	@ResponseBody
	public Object getRoomList(HttpServletRequest request){
		Account user = this.getSessionUser(request);
		if(user == null){
			return "KINDLY LOGIN TO ACCESS THIS API";
		}
		return roomRepository.findAll();
	}

	@PostMapping("/bookRoom")
	@ResponseBody
	public String bookRoom(@RequestBody BookingPojo bookingPojo, HttpServletRequest request){
		Account user = this.getSessionUser(request);
		if(user == null){
			return "KINDLY LOGIN TO ACCESS THIS API";
		}else{
			Long roomId = bookingPojo.roomId;
			Date startDate, endDate;
			try{
				startDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookingPojo.startDate);
				endDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookingPojo.endDate);
			}catch(Exception e){
				return "INVALID DATE FORMAT";
			}
			Room room = roomRepository.getById(roomId);
			if(room == null){
				return "ROOM NOT EXSISTS, GIVE VALID ROOM ID";
			}else{
				Long startTime = startDate.getTime();
				Long endTime = endDate.getTime();
				long difference = endTime - startTime;
				if(difference < 0){
					return "END DATE SHOULD BE GREATER THAN START DATE";
				}else{
			        long daysBetween = (long)(difference / (1000*60*60*24));
					if(room.maximumDays < daysBetween){
						return "BOOKING DAYS GOT EXCEEDED THAN LIMIT "+room.maximumDays+" DAYS";
					}else{
						List<Booking> roomBookingList = bookingRepository.findAllByRoomId(roomId);
						boolean canBook = true;
						for(Booking roomBooking : roomBookingList){
							Long alreadyBookedStartTime = roomBooking.startDateOfBooking.getTime();
							Long alreadyBookedEndTime = roomBooking.endDateOfBooking.getTime();
							boolean isConflicted = ((endTime < alreadyBookedStartTime && endTime < alreadyBookedEndTime) || 
							   (startTime > alreadyBookedStartTime && startTime > alreadyBookedEndTime));
							canBook&= isConflicted;
						}
						if(!canBook){
							return "DATES ARE ALREADY BOOKED BY ANOTHER CUSTOMER, KINDLY SELECT ANOTHER DATE";
						}else{
							try{
								Booking booking = new Booking(user.id,roomId,startDate,endDate,daysBetween*room.amountPerDay);
								bookingRepository.save(booking);
								return "ROOMS BOOKED SUCCESSFULLY";
							}
							catch(Exception e){
								return "ERROR OCCURED ON BOOKING, KINDLY TRY AFTER SOME TIME";
							}
						}
					}
				}
			}
		}
	}
}
