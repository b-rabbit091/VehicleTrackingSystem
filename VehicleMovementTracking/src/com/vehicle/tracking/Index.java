package com.vehicle.tracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Index {
		
	
	 boolean isSuccesfull(String uname, String pass) throws SQLException
	{
		
		if(uname.equals("admin") && pass.equals("admin"))
			return false;
		return true;
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc= new Scanner(System.in);		
		Index obj = new Index();
		System.out.println("*************WELCOME TO VEHICLE TRACKING SYSTEM*************");
		String uname="", pass="";
		boolean login=false;
		do
		{
			System.out.println("Enter correct username ");
			uname= sc.nextLine();
			
			System.out.println("Enter correct password ");
			pass= sc.nextLine();
			
			login = obj.isSuccesfull(uname,pass);
			
		}while(login);
		System.out.println("Login Successfull\n\n");
		System.out.println("Here are all the options:");
		boolean run_options= true;
		while(run_options)
		{
			System.out.println("1 : Add Location and Camera");
			System.out.println("2 : Add Vehicle");
			System.out.println("3 : Add Vehicle Movement");
			System.out.println("4 : Generate report of Vehicle Movement");
			System.out.println("5 : Exit");
			
			
			System.out.println("Please type in the option number you want to proceed with");
		
			String feature =sc.nextLine();
			
			char  feature_ch =feature.charAt(0);
			
			if(Character.isDigit(feature_ch))
			{
				
				
			int feature_no = Integer.parseInt(feature);
			
			
			while(feature_no <0 || feature_no >6)
			{
				System.out.println("Invalid number \nSelect your option");
				feature_no =sc.nextInt();
				
			}
			
			
			switch(feature_no) {
			
			case 1:
				
				new AddCameraLocation().main_ask();
				break;
				
			
			
			case 2:
				new AddVehicle().main_ask();
				break;
				
				
			case 3:
				new VehicleMovement().main_ask();
				break;
				
			case 4:
				new GenerateReport().main_ask();
				break;
				
			case 5:
				
				run_options=false;
				break;
		
		
		}
		

	}
			
			else
			{
				System.out.println("\nWrong option\nadmin");
			}
			
		
		}
		
System.out.println("*****************************Thank you*****************************");

}



	
}