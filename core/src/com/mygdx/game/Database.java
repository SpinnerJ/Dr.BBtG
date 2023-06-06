package com.mygdx.game;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import org.sqlite.SQLiteDataSource;

public class Database 
{
	private SQLiteDataSource ds;
	private Connection con;
	
	public Database()
	{
		ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:gameDB.db");
	}
	
	public void connect()
	{
		try 
		{
			con = ds.getConnection();
			System.out.println("Connected to database");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void createTable()
	{
		String query = "create table scores(name varchar(20),score int);";
		System.out.println(query);
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertScore(String name, int score)
	{
		String query = "insert into scores(rowid,name,score)values(null,'"+name+"',"+score+");";
		System.out.println(query);
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String queryAll()
	{
		String query = "select * from scores";
		String total = "";
		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next())
			{
				total+=(result.getString("name"))+" "+result.getString("score")+"\n";
			}
			return total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	
}