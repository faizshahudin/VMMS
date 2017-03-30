package com.xyz.vdms.model;

public class Money {
	private int value;
	private int moneyQuantity;
	private int moneyID;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getMoneyQuantity() {
		return moneyQuantity;
	}
	public void setMoneyQuantity(int moneyQuantity) {
		this.moneyQuantity = moneyQuantity;
	}
	public int getMoneyID() {
		return moneyID;
	}
	public void setMoneyID(int moneyID){
		this.moneyID = moneyID;
	}
}
