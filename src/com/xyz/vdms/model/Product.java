package com.xyz.vdms.model;

public class Product {
	private int productID;
	private String name;
	private double price;
	private int productQuantity;
	
	public int getProductID(){
		return productID;
	}
	
	public void setProductID(int productID){
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
}
