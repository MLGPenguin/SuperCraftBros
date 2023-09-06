package me.superpenguin.supercraftbros.objects;

import java.time.Duration;
import java.time.Instant;

public class Timer {
	
	private Instant start, end;
	
	public void start() { start = Instant.now(); }
	public void end() { end = Instant.now(); }

	
	public long getCurrentms() { return Duration.between(start, Instant.now()).toMillis(); }
	public long getms() { return Duration.between(start, end).toMillis(); }
}
