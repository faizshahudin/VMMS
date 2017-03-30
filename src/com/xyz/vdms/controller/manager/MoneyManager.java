package com.xyz.vdms.controller.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xyz.vdms.model.Money;

class MoneyManager extends AbstractTableManager<Money>{
	MoneyManager(Facade facade) {
		super(facade);
	}
	
	private void writeMoney(Money money, PreparedStatement ps) throws SQLException{
		//Set the values of SQL statement
		ps.setDouble(1, money.getValue());
		ps.setInt(2, money.getMoneyQuantity());
		
		if (money.getMoneyID() != 0)
			ps.setInt(3, money.getMoneyID());
	}

	int display() throws SQLException {
		
		PreparedStatement ps = facade.prepareStatement("SELECT * FROM money");
		
		ResultSet rs = ps.executeQuery();
		System.out.println(" ");
		System.out.println("ID\t" + "Value");
		
		while(rs.next()){
			System.out.println(rs.getInt("MoneyID") + "\tRM " + rs.getDouble("Value") + "0");
		}
		System.out.println();
		return 0;
	}
	
	int update(Money money) throws SQLException{
		// Create SQL statement
		PreparedStatement ps = facade.prepareStatement("UPDATE money SET Value = ?, MoneyQuantity = ? WHERE MoneyID = ?");
		
		writeMoney(money, ps);
		
		// Return the status
		return ps.executeUpdate();
	}

	public boolean checkMoneyID(int moneyID, Boolean validMoney) throws SQLException {
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT MoneyID FROM money WHERE MoneyID = ?");
		ps.setInt(1, moneyID);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			validMoney = true;
		}
		else{
			System.out.println("Invalid money, please try again.");
			validMoney = false;
		}
		return validMoney;
	}
	
	public double getValue(int moneyID) throws SQLException {
		double value = 0.0;
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT Value FROM money WHERE MoneyID = ?");
		ps.setInt(1, moneyID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			value = rs.getDouble("Value");
		}
		return value;
	}
	
	int addQuantity(int moneyID) throws SQLException{
		// Create SQL statement
		//System.out.println("in addQuantity");
		int qty = checkQuantity(moneyID);
		//System.out.println(qty);
		qty += 1;
		PreparedStatement ps = facade.prepareStatement("UPDATE money SET MoneyQuantity = ? WHERE MoneyID = ?");
		ps.setInt(1, qty);
		ps.setInt(2, moneyID);
		
		
		// Return the status
		return ps.executeUpdate();
	}
	
	int minusQuantity(int moneyID) throws SQLException {
		int qty = checkQuantity(moneyID);
		qty -= 1;
		PreparedStatement ps = facade.prepareStatement("UPDATE money SET MoneyQuantity = ? WHERE MoneyID = ?");
		ps.setInt(1,  qty);
		ps.setInt(2, moneyID);
		return ps.executeUpdate();
	}

	public int checkQuantity(int moneyID) throws SQLException {
		int quantity = 0;
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT MoneyQuantity FROM money WHERE MoneyID = ?");
		ps.setInt(1, moneyID);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			 quantity = rs.getInt("MoneyQuantity");
		}
		return quantity;
	}

	public boolean sufficient(double balance) throws SQLException {
		int moneyQuantity[] = new int[6];
		int t = 0;
		PreparedStatement ps = null;
		ps = facade.prepareStatement("SELECT MoneyQuantity FROM money");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			moneyQuantity[t] = rs.getInt("MoneyQuantity");
			t++;
		}
		int temp = 0;
		double bal = 0.0;
		for(temp = 5; temp >= 0 ; temp--){
			//System.out.println("testInLoop: " + balance);
			bal = balance - facade.getValue(temp);
			if (bal >= 0){
				if(moneyQuantity[temp] > 0){
					balance = balance - facade.getValue(temp);
					//System.out.println("testInLoopIf: " + balance);
					moneyQuantity[temp] -= 1;
					temp+=1;
				}
			}
		}
		if(bal>0.09){
			return false;
		}
		return true;
	}

}
