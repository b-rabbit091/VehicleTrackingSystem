package com.vehicle.tracking;

import java.sql.Connection;
import java.io.FileNotFoundException;
import  java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
public class GenerateReport {
	
	static  int count=0;
	static Connection con=null;
	static Statement stmt = null;
	static String url = "jdbc:sqlite:VD2"+ ".db";
	static String new_location=null;
	static Scanner sc= new Scanner(System.in);
	GenerateReport() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("org.sqlite.JDBC");
		this.con = DriverManager.getConnection(url);
		this.stmt = con.createStatement();	
	}
	
	
	void Generate_pdf_report(String report)
	{Document document = new Document();
    try
    {
    	
       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./report.pdf"));
       document.open();
       document.add(new Paragraph(report));
       document.close();
       writer.close();
       System.out.println("Report has been saved is report.pdf in current directory");
    } catch (DocumentException e)
    {
       e.printStackTrace();
    } catch (FileNotFoundException e)
    {
       e.printStackTrace();
    }
    return;
			
	}
	
	void retrieve_report() throws SQLException
	{
		String sql = "select * from  Vehicle_Movement";// where Vehicle = ('"+vehicle_no+"')";
		ResultSet rs= stmt.executeQuery(sql);
		String report="";
		report +="Vehicle                     Movements\n";
		while(rs.next())
		{
			
			report+= rs.getString("Vehicle");
			
			report +="                     ";
			report+=rs.getString("Movement");
			report +="\n\n";
			
			
		}

		
		System.out.println(report);
		
		this.Generate_pdf_report(report);
		return;
		
		
	}
	
	void main_ask() throws SQLException
	{
		
		this.retrieve_report();
		return;
	}
	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	/*
	
	*/
	
	
	
}}
