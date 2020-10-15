package com.compucompare;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.compucompare.compucompare.App;
import com.compucompare.compucompare.components.BatteryComponent;
import com.compucompare.compucompare.components.RAMComponent;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

@ActiveProfiles("test")
@SpringBootTest(classes = App.class)

public class BatteryEqualsTest {
	
	private BatteryComponent test1;
	private BatteryComponent test2;
	private BatteryComponent test3;
	private RAMComponent test4;
	

	@BeforeEach
	public void setUp() throws Exception {
		test1 = new BatteryComponent("Brand 1","Model 1", 2, 100);
		test2 = new BatteryComponent("Brand 2","Model 2", 1, 200);
		test3 = new BatteryComponent("Brand 1","Model 1", 2, 100);
		test4 = new RAMComponent("Brand 1","Model 1", 2, 100, true);
	}

	@Test
	public void test() {
		assertTrue(test1.equals(test3));
		assertTrue(test1.equals(test1));
		assertFalse(test1.equals(test2));
		assertFalse(test2.equals(test1));
		assertFalse(test2.equals(test3));
		assertTrue(test2.equals(test2));
		assertTrue(test3.equals(test1));
		assertTrue(test3.equals(test3));
		assertFalse(test3.equals(test2));
		assertFalse(test4.equals(test1));
		assertFalse(test4.equals(test2));
		assertFalse(test4.equals(test3));		
	}

}
