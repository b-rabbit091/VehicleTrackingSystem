package com.vehicle.tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddVehicle {
	

	static Connection con=null;
	static Statement stmt = null;
	static String url = "jdbc:sqlite:VD2"+ ".db";
	static String new_location=null;
	static Scanner sc= new Scanner(System.in);
	static String new_vehicle_no=null;
	
	AddVehicle() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("org.sqlite.JDBC");
		this.con = DriverManager.getConnection(url);
		this.stmt = con.createStatement();	
	}
	
	  void establishConnection() throws ClassNotFoundException, SQLException
		{
			  
			
			  	
				String sql1 = "Create table IF NOT EXISTS Vehicles(Vehicle varchar)";
				
				stmt.execute(sql1);
				
				
			//	System.out.println("Connection established.....");
				
				
		}
	  
	  
	  boolean check_availability_location(String lo) throws SQLException, ClassNotFoundException
		 {
			  
				
				String sql = "select * from  Vehicles where Vehicle = ('"+lo+"')";
				ResultSet rs= stmt.executeQuery(sql); 
				while(rs.next())
				{
					System.out.println("Exist with the given vehicle number");
					return true;
				}
				
			
				this.insert_location(lo);
				
			 return false;
		 }
	  
	  boolean insert_location(String location) throws ClassNotFoundException, SQLException
		 {
		
				
				String sql = "INSERT INTO Vehicles VALUES ('"+location+"')";
				stmt.executeUpdate(sql);
				System.out.println("Vehicle number added");
		
				
			 return true;
		 }	  
	
		
	  
	  void main_ask() throws ClassNotFoundException, SQLException
		{
		  	System.out.println("-------------------------------------------");
			System.out.println("You have choosen to add vehicle");
			
		  
			AddVehicle obj = new AddVehicle();
			
			
				obj.establishConnection();
				
		      
			  do { 
				  System.out.println("\nPlease enter your Vehicle name:\n Provide it in 4 numeric digit"); 
			  System.out.println("Ex: 1234");
				  new_vehicle_no =sc.nextLine(); 
			  new_vehicle_no = new_vehicle_no.toUpperCase();
			 
			   }while(obj.check_availability_location(new_vehicle_no)); 
			 	
			  
			  System.out.println("\n\n Successfully Vehicle added.");
		
		
		}
		
	  
	  
		 
		 
	

	public static void main(String[] args) {
		

	}

}
