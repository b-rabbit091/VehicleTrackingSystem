package com.vehicle.tracking;
import java.sql.*;
import java.util.*;
public class AddCameraLocation {
	
	static Connection con=null;
	static Statement stmt = null;
	static String url = "jdbc:sqlite:VD2"+ ".db";
	static String new_location=null;
	static Scanner sc= new Scanner(System.in);
	
	AddCameraLocation() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("org.sqlite.JDBC");
		this.con = DriverManager.getConnection(url);
		this.stmt = con.createStatement();	
	}
	
	
	
	 
	
	  void establishConnection() throws ClassNotFoundException, SQLException
	{
		 
			String sql1 = "Create table IF NOT EXISTS Locations3(location varchar(255))";
			String sql2 = "Create table IF NOT EXISTS Cameras(location varchar(255), C1 int , C2 int ,C3 int, C4 int,C5 int)";
			stmt.execute(sql1);
			stmt.execute(sql2);
			
			//System.out.println("Connection established.....");
			
			
	}
	 
	 
	 
	  boolean check_availability_location(String location) throws SQLException, ClassNotFoundException
	 {
		  
			
			String sql = "select * from  Locations3 where location = ('"+location+"')";
			ResultSet rs= stmt.executeQuery(sql); 
			while(rs.next())
			{
				System.out.println("exist with the given location");
				return true;
			}
			
		
			this.insert_location(location);
			
		 return false;
	 }
	 
	  
	 boolean insert_location(String location) throws ClassNotFoundException, SQLException
	 {
	
			
			String sql = "INSERT INTO Locations3 VALUES ('"+location+"')";
			stmt.executeUpdate(sql);
			System.out.println("Location added");
	
			int camera_no=0;
			do {
				System.out.println("\nHow many cameras you want to install in "+location+"?");
				System.out.println("Max Cameras allowed 5");
				camera_no = sc.nextInt();
				sc.nextLine();
				
			}while(camera_no <0 || camera_no>5);
			
			PreparedStatement pre_stmt=con.prepareStatement("insert into Cameras values(?,?,?,?,?,?)");  
			pre_stmt.setString(1, location);
			for(int no=2;no<=6;no++)
			{
				if(no<=camera_no+1)
				pre_stmt.setInt(no,1);
				else
				pre_stmt.setInt(no,0);
			}
			pre_stmt.executeUpdate();  
			System.out.println(camera_no+" Cameras inserted in"
					+location);  
			
			
			query(location);
		 return true;
	 }
	 
	 
	 
	void query(String location) throws ClassNotFoundException, SQLException
	 {
		
		
		String sql = "select * from  Cameras where location = ('"+location+"')";
		ResultSet rs= stmt.executeQuery(sql); 
		
		rs.next();
		System.out.println("\nDescription about added locations: ");
		System.out.println("\n"+"Location = "+location+" : "+"");
			
			System.out.println("Camera 1(C1) : "+ rs.getString("C1"));
			System.out.println("Camera 2(C2) : "+ rs.getString("C2"));
			
			System.out.println("Camera 3(C3) : "+ rs.getString("C3"));
			System.out.println("Camera 4(C4) : "+ rs.getString("C4"));
			System.out.println("Camera 5(C5) : "+ rs.getString("C5"));
		
		stmt.close();
		con.close();
		
	 }
	
	void main_ask() throws ClassNotFoundException, SQLException
	{
		System.out.println("-------------------------------------------");
		System.out.println("You have choosen to add location and camera");
		System.out.println("");
		AddCameraLocation obj = new AddCameraLocation();
		
		
			obj.establishConnection();
			
	      
		  do { 
			  System.out.println("\nPlease enter your location name:\n Provide it in 3 digit code"); 
			  System.out.println("Example: Kathmandu : KTM");
			  
		  new_location =sc.nextLine(); 
		   new_location = new_location.toUpperCase(); 
		   }while(obj.check_availability_location(new_location)); 
		 	
		  
		  System.out.println("\n Successfully location added.");
	
	
	}
	
	
	
	 

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
		
		
		
		
		  
		System.out.println("aba eta aaiyo");
		
		//obj.query("RTP");
		
		
		
		
		
		
	}
}
