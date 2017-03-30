package com.xyz.vdms.controller.manager;

import java.sql.*;
import com.xyz.vdms.model.Sale;

class SaleManager extends AbstractTableManager<Sale>{
	SaleManager(Facade facade){
		super (facade);
	}
	
	private void writeSale(Sale sale, PreparedStatement ps) throws SQLException {
		// Set the values of SQL statement
				ps.setInt(1, sale.getProductID());
				ps.setInt(2, sale.getSaleQuantity());
				ps.setDouble(3, sale.getAmount());
				ps.setTimestamp(4, sale.getSaleTime());

				if (sale.getSaleID() != 0)
					ps.setInt(5, sale.getSaleID());
	}
	
	int add(Sale sale) throws SQLException
	{
		// Create SQL statement
		PreparedStatement ps = facade.prepareStatement("INSERT INTO sale (ProductID, SaleQuantity, Amount, SaleTime) VALUES (?, ?, ?, ?)");

		writeSale(sale, ps);

		// Send the statement and get the number of rows affected
		int status = ps.executeUpdate();

		if (status != 0)
		{
			// Get the auto-incremented key
			ResultSet rs = ps.getGeneratedKeys();

			// If successfully generated
			if (rs.next())
				sale.setSaleID(rs.getInt(1));
		}

		// Return the status
		return status;
	}
	
	int createSale(int id, int qty, double price) throws SQLException{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Sale sale = new Sale();
		sale.setProductID(id);
		sale.setSaleQuantity(qty);
		sale.setAmount(price);
		sale.setSaleTime(timestamp);
		add(sale);
		return 0;
	}
}
