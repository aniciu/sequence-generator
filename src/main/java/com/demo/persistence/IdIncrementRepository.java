package com.demo.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
//@EnableScan
public class IdIncrementRepository implements DAOIdIncrement {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public long getNextIncrement() {
		
		long key = -1;
		
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();		
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO sequence_id (name) VALUES (?)",  PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, "a");
		
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
	
			if (rs.next()) {
			    key = rs.getLong(1);
			}
		} catch (SQLException ex) {
			//log and rethrow
			//throw new ApplicationException("500", ex.getMessage(), "");
			throw new RuntimeException(ex.getMessage());
		}
		return key;
	}
}
