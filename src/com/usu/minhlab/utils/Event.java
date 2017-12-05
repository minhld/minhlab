package com.usu.minhlab.utils;

import java.util.Date;

public class Event {
	public enum Type {
		Nothing,	// light off and no moving
		Moving,		// moving (doesn't care about light)
		LightOn,	// light on, no move
		LightOff,	// light off, no move
	}
	
	public long time;
	public String type;
	public String info;
	
	public Event(String type) {
		this.time = new Date().getTime();
		this.type = type;
		this.info = "";
	}
	
	public Event(String type, String info) {
		this.time = new Date().getTime();
		this.type = type;
		this.info = info;
	}
	
	public Event(long time, String type, String info) {
		this.time = time;
		this.type = type;
		this.info = info;
	}
}
