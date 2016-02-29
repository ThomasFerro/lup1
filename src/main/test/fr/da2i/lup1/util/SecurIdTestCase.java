package fr.da2i.lup1.util;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

public class SecurIdTestCase {

	@Test
	public void test() throws InterruptedException {
		SecurId myId = new SecurId("leleuj");
		assertFalse(myId.hasExpire());
		myId.setExpirationDate(new Timestamp(System.currentTimeMillis()));
		Thread.sleep(100);
		assertTrue(myId.hasExpire());
	}

}
