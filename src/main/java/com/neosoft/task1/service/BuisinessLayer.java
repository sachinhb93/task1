package com.neosoft.task1.service;

public class BuisinessLayer {
	
	public int calculateSum(int [] data)
	{
		int sum=0;
		for (int i : data) {
			sum=sum+i;
			
		}
		return sum;
	}

}
