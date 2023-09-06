package me.superpenguin.supercraftbros.objects;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import me.superpenguin.supercraftbros.utils.u;


public class Debugger {
	
	private List<Instant> instants;
	private String colour, name;
	private TimeUnit timeunit;
	
	public Debugger(String name, String colour, TimeUnit unit) {	
		instants = new ArrayList<>();
		timeunit = unit;
		this.colour = u.cc(colour);
		this.name = name;
		addPoint();
	}
	
	public void addPoint() {  instants.add(Instant.now()); }
	public void finish() { addPoint(); }
	public long getTime() { return between(instants.get(0), instants.get(instants.size()-1)); }
	
	
	public String getTimeMsg() {
		String msg = colour + "(" + name + ") [";
		int l = instants.size();
		for (int i = 0 ; i < (l-1) ; i++) msg += u.dc(between(instants.get(i), instants.get(i+1))) + (timeunit.getSuffix() + " + ");		
		return msg.substring(0, msg.length()-3) + ((l > 2) ? (" = " + u.dc(between(instants.get(0), instants.get(l-1))) + (timeunit.getSuffix()) + "]") : "]");
	}
	
	private long between(Instant a, Instant b) { 
		Duration d = Duration.between(a, b);
		switch (timeunit) {
			case MICRO: return d.toNanos()/1000;
			case MILLI: return d.toMillis();
			case NANO: return d.toNanos();
			default: return d.toMillis();
		}  
	}	
	
	public enum TimeUnit { 
		NANO("ns"), MICRO("us"), MILLI("ms"); 		
		private String suffix;
		TimeUnit(String suffix) {
			this.suffix = suffix;
		} 
		public String getSuffix() { return suffix; }
	}	
}