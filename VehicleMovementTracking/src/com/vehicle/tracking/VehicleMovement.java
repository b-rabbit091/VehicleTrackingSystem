package com.vehicle.tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VehicleMovement {
	
	
	 
		static Connection con=null;
		static Statement stmt = null;
		static String url = "jdbc:sqlite:VD2"+ ".db";
		static String new_location=null;
		static Scanner sc= new Scanner(System.in);
		int [] cams = new int[5];
		ArrayList<String> locations = new ArrayList<String>();
		ArrayList<String> vehicles = new ArrayList<String>();
		Map <String,Integer>loc_cam=new HashMap <String,Integer>();  
		
		VehicleMovement() throws ClassNotFoundException, SQLException
		{
			
			Class.forName("org.sqlite.JDBC");
			this.con = DriverManager.getConnection(url);
			this.stmt = con.createStatement();	
		}
		
		
		  void establishConnection() throws ClassNotFoundException, SQLException
		{
			  
			 
				//  System.out.println("Connection already established...");
				 
			  String sql1 = "Create table IF NOT EXISTS Vehicle_Movement(Vehicle varchar, Movement varchar)";
				
				stmt.execute(sql1);
				
				
				
				
		}
		 
		 
	
	void main_ask() throws SQLException, ClassNotFoundException
	{
		
		System.out.println("-------------------------------------------");
		System.out.println("You have choosen to add vehicle movement");
		
		
		VehicleMovement vm = new VehicleMovement();
		vm.establishConnection();
		
		
		String available_locations = "select * from Cameras";
		ResultSet rs= stmt.executeQuery(available_locations); 
		
		
		
		
			
		
		while(rs.next())
		{	int counter=0;
			System.out.print("Location = ");
			System.out.println(rs.getString(1));
			String location = rs.getString(1);
			locations.add(location);
			System.out.println("-----Available Cameras-------");
			System.out.print("C1:"+rs.getInt(2)+" ");
			System.out.print(",C2:"+rs.getInt(3)+" ");
			System.out.print(",C3:"+rs.getInt(4)+" ");
			System.out.print(",C4:"+rs.getInt(5)+" ");
			System.out.println(",C5:"+rs.getInt(6));
			System.out.println("------------------------------");
			
			counter = rs.getInt(2)+rs.getInt(3)+rs.getInt(4)+rs.getInt(5)+rs.getInt(6);
			loc_cam.put(location,counter);	
		}
		
		String available_vehicles= "select * from Vehicles";
		ResultSet rs_veh = stmt.executeQuery(available_vehicles);
		System.out.println("Available vehicles: ");
		while(rs_veh.next())
		{
			vehicles.add(rs_veh.getString("Vehicle"));
			
			System.out.println(rs_veh.getString("Vehicle"));
		}
		
		
		
		
		
			this.ask_movement();
		
		
		
		}
	
		
		void ask_movement() throws ClassNotFoundException, SQLException
		{
			
			String more_locations=null;
			String cam_movement = "";
			String user_vehicle="";
			
			while((!vehicles.contains(user_vehicle)))
			{
				System.out.println("Give the Available Vehicle no");
				user_vehicle =sc.nextLine();
			}
			
			
			do {
				String travelled_location = null;
				more_locations="n";
				
				while((!locations.contains(travelled_location)))
			{
					
				System.out.println("Give the Available location");
				travelled_location =sc.nextLine();
				
				travelled_location=travelled_location.toUpperCase();
				

				}
				
				
				cam_movement+=travelled_location;
			int cameras = loc_cam.get(travelled_location);
			System.out.println("There are "+cameras+"cameras  in "+travelled_location);
			
			
			int i=1;
			while(i<=cameras)
			{
				
				String choice =null;
				boolean other_option=false;
				
				do {System.out.println("Did the vehicle moved through C"+i+"(y/n)?");
					choice =sc.nextLine();
					if(choice.equals("y"))
					{
					cam_movement+="C"+i;
					break;
					}
					
					else if(choice.equals("n"))
					{
						break;
					}
					else
						other_option= true;
				}
				
				while(other_option);
				i=i+1;
					
			}
			
			System.out.println("Are there more locations?(y/n)");
			more_locations=sc.nextLine();
			
		cam_movement+="  ";	
		
			}while(more_locations.equals("y"));
			
			System.out.println(user_vehicle +" movement is along the path "+cam_movement);
			insert_vehicle_movement(user_vehicle,cam_movement);
				
		}
		
		  
		  boolean insert_vehicle_movement(String user_vehicle, String cam_movement) throws ClassNotFoundException, SQLException
			 {
			
			  PreparedStatement pre_stmt=con.prepareStatement("insert into Vehicle_Movement values(?,?)");  
				pre_stmt.setString(1, user_vehicle);
				pre_stmt.setString(2, " "+cam_movement);
				pre_stmt.executeUpdate();
			  System.out.println("Vehicle along with Path saved");
								
			
					
				 return true;
			 }	
			
		
		
		
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		//vm.main_ask();
		
		
	}

}
