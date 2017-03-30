package com.xyz.vdms.controller.manager;

import java.sql.*;

import com.xyz.vdms.model.Product;
import com.xyz.vdms.model.Sale;

public class Facade implements AutoCloseable {
	
	private Connection connection;
	private ProductManager productManager;
	private MoneyManager moneyManager;
	private SaleManager saleManager;
	static
	{
		try{
			//Load the database driver
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
			System.err.println("Unable to load database driver. System will exit now.");
			System.exit(0);
		}
	}
	
	private Connection getConnection() throws SQLException{
		//Create database connection
		if(connection == null || connection.isClosed()){
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VDMS", "root", "");
			connection.setAutoCommit(false);
		}
		
		return connection;
	}
	// to check if class manager exist
	private ProductManager getProductManager(){
		if (productManager == null)
			productManager = new ProductManager(this);
		
		return productManager;
	}
	
	private SaleManager getSaleManager(){
		if (saleManager == null)
			saleManager = new SaleManager(this);
		
		return saleManager;
	}
	
	private MoneyManager getMoneyManager(){
		return moneyManager == null? moneyManager = new MoneyManager(this) : moneyManager;
	}
	
	PreparedStatement prepareStatement(String sql) throws SQLException{
		return getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	}
	
	@Override
	public void close() throws SQLException{
		if (connection != null && !connection.isClosed()){
			connection.commit();
			connection.close();
		}
	}
	
	public int updateProduct(Product product) throws SQLException {
		return getProductManager().update(product);
	}
	
	public int displayProduct() throws SQLException {
		return getProductManager().display();
	}
	
	public Boolean checkProduct(int id,Boolean bool) throws SQLException {
		return getProductManager().checkProductID(id,bool);
	}
	
	public int checkProductQuantity(int id) throws SQLException {
		return getProductManager().checkQuantity(id);
	}
	
	public double getPrice(int id) throws SQLException {
		
		return getProductManager().getPrice(id);
	}
	public int addSale(Sale sale) throws SQLException {
		return getSaleManager().add(sale);
	}
	public int displayMoney() throws SQLException {
		return getMoneyManager().display();
		
	}
	public boolean checkMoneyID(int moneyID, Boolean validMoney) throws SQLException {
		
		return getMoneyManager().checkMoneyID(moneyID, validMoney);
	}
	
	public double getValue(int moneyID) throws SQLException {
		return getMoneyManager().getValue(moneyID);
	}
	public int checkQuantity(int moneyID) throws SQLException {
		return getMoneyManager().checkQuantity(moneyID);
	}
	
	public int addQuantity(int moneyID) throws SQLException {
		return getMoneyManager().addQuantity(moneyID);
	}
	
	public int minusQuantity(int moneyID) throws SQLException {
		return getMoneyManager().minusQuantity(moneyID);
	}
	public int updateProductQuantity(int id, int qty) throws SQLException {
		return getProductManager().updateQuantity(id, qty);
	}
	
	public int createSale(int id, int qty, double price) throws SQLException {
		return getSaleManager().createSale(id, qty, price);
	}
	
	public boolean sufficientAmount(double balance) throws SQLException {
		return getMoneyManager().sufficient(balance);
	}
}
