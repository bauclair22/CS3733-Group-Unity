package databaseTest;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;

public class forme {

	@Test
	public void test() {
		float days = 2.5f;
		System.out.println("days float: " +days);
		LocalDateTime myTime = LocalDateTime.now();
		Timestamp today= Timestamp.valueOf(myTime);
		System.out.println("today in long: "+ today.getTime());
		System.out.println("today : "+ today.toString());
		float milli = 86400000 * days;
		System.out.println("days in mili float:" + milli);
		long milliseconds = (long) milli;
		System.out.println("days in mili long:" +milliseconds);
		long time = today.getTime()- milliseconds;
		System.out.println("expiration in long:"+ time);
		Timestamp expiration = new Timestamp(time);
		System.out.println("expiration :"+ expiration.toString());
		long remainder = time%1000;
		System.out.println("remainder :"+ remainder);
		long timeRounded = time-remainder;
		Timestamp newexpiration = new Timestamp(timeRounded);
		System.out.println("new Time :"+ newexpiration.toString());
		today.setTime(timeRounded);
		System.out.println("set new Time :"+ today.toString());
	}

}
