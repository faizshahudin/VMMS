package com.xyz.vdms.model;

import java.sql.Timestamp;

public class Sale {
	private int saleID;
	private int productID;
	private int saleQuantity;
	private double amount;
	private Timestamp saleTime;
	
	public int getSaleID() {
		return saleID;
	}
	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double price) {
		this.amount = price;
	}
	public Timestamp getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Timestamp saleTime) {
		this.saleTime = saleTime;
	}
	
}
