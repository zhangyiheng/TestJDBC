package com.hand.TestExamJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExamJDBC2 {
	public static Connection getConn() {
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

	public static void RendFilmInfo(int CustomerID) {
		Connection conn = getConn();

		try {

//			String sql = "SELECT inventory.film_id from rental,customer,inventory WHERE rental.inventory_id = inventory.inventory_id AND rental.customer_id = customer.customer_id AND customer.customer_id="
//					+ CustomerID;
			String sql = "SELECT inventory.film_id,film.title,rental_date FROM rental,film,inventory WHERE rental.inventory_id= inventory.inventory_id AND inventory.film_id= film.film_id AND rental.customer_id ="+ CustomerID+" ORDER BY rental_date DESC;";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSet rs1[] = new ResultSet[100];
			int i = 0;
			System.out.println("JOHNNIE.CHISHOLM 租用的Film-­‐>");
			System.out.println("FilmID|Film名称|租用时间");
			
			while (rs.next()){
				System.out.print(rs.getInt("film_id")+"|");
				System.out.print(rs.getString("title")+"|");
				System.out.print(rs.getDate("rental_date"));
				System.out.println();

			}
			
//			while (rs.next()) {
//				String sql1 = "SELECT inventory.film_id,film.title,rental_date FROM rental,film,inventory WHERE rental.inventory_id= inventory.inventory_id AND "+rs.getInt("film_id") +"= film.film_id;";
//				Statement st1 = conn.createStatement();
//				rs1[i] = st.executeQuery(sql1);
//				i++;
//
//			}
//			if(rs!=null){
//				rs.close();
//			}
//
//			for (int j = 0; j < rs1.length; j++) {
//				while (rs1[j].next()){
//					System.out.println("JOHNNIE.CHISHOLM 租用的Film-­‐>");
//					System.out.println("FilmID|Film名称|租用时间");
//					System.out.print(rs.getInt("film_id|"));
//					System.out.println(rs.getString("title"));
//					System.out.println(rs.getDate("rental_date"));
//
//				}
//			}
//			

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入CustomerID:");
		int CustomerID = input.nextInt();
		RendFilmInfo(CustomerID);

	}

}
