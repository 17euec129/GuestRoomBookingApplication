package com.example.springboot;

public class RoomPhoto{
	public Long	roomId;
	public Long	photoId;

	public RoomPhoto(Long roomId, Long photoId){
		this.roomId = roomId;
		this.photoId = photoId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

}