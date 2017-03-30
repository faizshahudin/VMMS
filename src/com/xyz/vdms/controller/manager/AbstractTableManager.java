package com.xyz.vdms.controller.manager;

import java.sql.*;

abstract class AbstractTableManager<Model> {
	protected Facade facade;
	
	AbstractTableManager(Facade facade){
		this.facade = facade;
	}
	
	protected Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	

	int delete(String table, int id) throws SQLException{
		//Create SQL statement
		PreparedStatement ps = facade.prepareStatement("DELETE FROM" + table + "WHERE" + table + "ID = ?");
		//Set the values of SQL statement
		ps.setInt(1, id);
		//Return the status
		return ps.executeUpdate();
	}
}
