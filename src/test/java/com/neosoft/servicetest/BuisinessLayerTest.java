package com.neosoft.servicetest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.neosoft.task1.service.BuisinessLayer;

public class BuisinessLayerTest {

	@Test
	public void calculateSumBasicSceneario() {

		BuisinessLayer b = new BuisinessLayer();
		int actualresult = b.calculateSum(new int[] { 1, 2, 3 });
		int expected = 6;
		assertEquals(expected, actualresult);
 
	}

}
