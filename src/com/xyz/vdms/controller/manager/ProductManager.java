package com.xyz.vdms.controller.manager;

import java.sql.*;

import com.xyz.vdms.model.Product;

class ProductManager extends AbstractTableManager<Product>{
	ProductManager(Facade facade){
		super(facade);
	}
	
	private void writeProduct(Product product, PreparedStatement ps) throws SQLException{
		//Set the values of SQL statement
		ps.setString(1, product.getName());
		ps.setDouble(2, product.getPrice());
		ps.setInt(3, product.getProductQuantity());
		
		if (product.getProductID() != 0)
			ps.setInt(4, product.getProductID());
	}
	
	int update(Product product) throws SQLException{
		// Create SQL statement
		PreparedStatement ps = facade.prepareStatement("UPDATE product SET Name = ?, Price = ?, ProductQuantity = ? WHERE ProductID = ?");
		
		writeProduct(product, ps);
		
		// Return the status
		return ps.executeUpdate();
	}
	
	int display() throws SQLException {
		
		PreparedStatement ps = facade.prepareStatement("SELECT * FROM product");
		
		ResultSet rs = ps.executeQuery();
		System.out.println("ID\t" + "Name\t" + "Price\t\t" + "Quantity" );
		
		while(rs.next()){
			System.out.println(rs.getInt("ProductID") + "\t" + rs.getString("Name") + "\t" + "RM " + rs.getDouble("Price") +"0"+ "\t\t" + rs.getInt("ProductQuantity"));
		}
		System.out.println();
		return 0;
	}
	
	Boolean checkProductID(int id, Boolean bool) throws SQLException {
		//Create SQL statement
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT ProductID FROM product WHERE ProductID = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			bool = false;
			if(facade.checkProductQuantity(id) == 0){
				System.out.println("Insufficient quantity, please try again.");
				bool = true;
			}
		}
		else{
			System.out.println("Invalid product ID, please try again.");
		}
		return bool;
	}
	
	/*
	public boolean checkProductQuantity(int id, int qty, boolean valid) throws SQLException {
		int compare = 0;
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT ProductQuantity FROM product WHERE ProductID = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			compare = rs.getInt("ProductQuantity");
		}
		if(qty <= compare){
			valid = false;
		}
		else{
			System.out.println("Insufficient quantity, please try again");
		}
		return valid;
	}

*/
	
	public int checkQuantity(int productID) throws SQLException {
		int quantity = 0;
		//System.out.println("in checkQuantity");
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT ProductQuantity FROM product WHERE ProductID = ?");
		ps.setInt(1, productID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			 quantity = rs.getInt("ProductQuantity");
		}
		//System.out.println("quantity: " + quantity);
		return quantity;
	}
	
	public double getPrice(int id) throws SQLException {
		double price = 0.0;
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT Price FROM product WHERE ProductID = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			price = rs.getDouble("Price");
		}
		return price;
	}

	int updateQuantity(int productID, int qty) throws SQLException {
		int num = checkQuantity(productID);
		num -= qty;
		PreparedStatement ps = facade.prepareStatement("UPDATE product SET ProductQuantity = ? WHERE ProductID = ?");
		ps.setInt(1,  num);
		ps.setInt(2, productID);
		return ps.executeUpdate();
	}
}
