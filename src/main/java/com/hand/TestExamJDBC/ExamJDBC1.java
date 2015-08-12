package com.hand.TestExamJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExamJDBC1 {
	
	public static Connection getConn(){
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sakila", "root", "123456");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void cityName(int CountryID){
		Connection conn = getConn();
		
		try {
			
			String sql = "select city from city where city_id = "+ CountryID;
			Statement st = conn.createStatement();
			ResultSet rs =  st.executeQuery(sql);
			
			System.out.println("CountrySpain 的城市-­‐>");
			System.out.println("城市ID|城市名称");
			while(rs.next()){
				System.out.println(CountryID+"    |"+rs.getString("city"));
				
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入CountryID:");
		int CountryID = input.nextInt();
		cityName(CountryID);
		
	}

}
