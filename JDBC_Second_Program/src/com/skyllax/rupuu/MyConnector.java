package com.skyllax.rupuu;

import java.sql.*;
import java.util.Scanner;

public class MyConnector {

	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSetMetaData rsmd;
	private static CallableStatement cstmt;

	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String username = "root";
			String password = "neha928@";
			String url = "jdbc:mysql://localhost:3306/jdbc_pratic";
			return DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void close(ResultSet res, Statement stmt, Connection con) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void fetchDataById() {
		Connection con;
		PreparedStatement pstmt;
		ResultSet res;

		try {
			con = connect();
			String sql = "select * from student where sid=?";
			pstmt = con.prepareStatement(sql);
			System.out.println("Enter the student ID :");
			pstmt.setInt(1, new Scanner(System.in).nextInt());
			res = pstmt.executeQuery();

			if (res.next()) {
				System.out.println("Student Records:");
				System.out.println("Student id: " + res.getInt("sid") + " " + "Student name: " + res.getString("sname")
						+ " " + "Student Email: " + res.getString("scourse") + " " + "Course taken: "
						+ res.getString("semail"));
			} else {
				System.out.println("No found Data");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void fetchData() {
		Connection con;
		Statement stmt;
		ResultSet res;

		try {
			con = connect();
			stmt = con.createStatement();
			res = stmt.executeQuery("SELECT * FROM student");

			/*
			 * System.out.println("Student Records:"); while (res.next()) {
			 * System.out.println("Student id: " + res.getInt(1) + " " + "Student name: " +
			 * res.getString(2) + " " + "Student Email: " + res.getString(3) + " " +
			 * "Course taken: " + res.getString(4)); }
			 */

			System.out.println("Student Records:");
			while (res.next()) {
				System.out.println("Student id: " + res.getInt("sid") + " " + "Student name: " + res.getString("sname")
						+ " " + "Student Email: " + res.getString("scourse") + " " + "Course taken: "
						+ res.getString("semail"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public static void insertData() {
		con = MyConnector.connect();
		String sql = "insert into student(sid,sname,semail,scourse) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the id");
			pstmt.setInt(1, sc.nextInt());
			sc.nextLine();

			System.out.println("Enter the Name");
			pstmt.setString(2, sc.nextLine());

			System.out.println("Enter the Email");
			pstmt.setString(3, sc.nextLine());

			System.out.println("Enter the Course");
			pstmt.setString(4, sc.nextLine());

			int n = pstmt.executeUpdate();
			System.out.println(n + " of row affected");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void updateData() {
		con = MyConnector.connect();
		String sql = "update student set scourse=? where sid=?";
		try {
			pstmt = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the id");
			pstmt.setInt(2, sc.nextInt());
			sc.nextLine();
			System.out.println("Enter the Course");
			pstmt.setString(1, sc.nextLine());

			int n = pstmt.executeUpdate();
			System.out.println(n + " of row affected");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void deleteData() {
		con = MyConnector.connect();
		String sql = "delete from student  where sid=?";
		try {
			pstmt = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the id");
			pstmt.setInt(1, sc.nextInt());
			sc.nextLine();

			int n = pstmt.executeUpdate();
			System.out.println(n + " of row affected");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	// using Transaction
//	public static void insertMulData() {
//		con = MyConnector.connect();
//		String sql = "insert into student(sid,sname,semail,scourse) values(?,?,?,?)";
//		try {
//			pstmt = con.prepareStatement(sql);
//			Scanner sc = new Scanner(System.in);
//			con.setAutoCommit(false);
//			int n = 0;
//			System.out.println("How many student do you enter");
//			int num = sc.nextInt();
//			for (int i = 0; i < num; i++) {
//
//				System.out.println("Enter the id");
//				pstmt.setInt(1, sc.nextInt());
//				sc.nextLine();
//
//				System.out.println("Enter the Name");
//				pstmt.setString(2, sc.nextLine());
//
//				System.out.println("Enter the Email");
//				pstmt.setString(3, sc.nextLine());
//
//				System.out.println("Enter the Course");
//				pstmt.setString(4, sc.nextLine());
//
//			n = n + pstmt.executeUpdate();
//	
//			System.out.print(n+""+" row is affected");
//			}
//			con.commit();
//			
//			System.out.println(n + " of row affected");
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//	}
	
	//using BatchUpdate
	public static void insertMulData() {
		con = MyConnector.connect();
		String sql = "insert into student(sid,sname,semail,scourse) values(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			
			int n = 0;
			System.out.println("How many student do you enter");
			int num = sc.nextInt();
			for (int i = 0; i < num; i++) {

				System.out.println("Enter the id");
				pstmt.setInt(1, sc.nextInt());
				sc.nextLine();

				System.out.println("Enter the Name");
				pstmt.setString(2, sc.nextLine());

				System.out.println("Enter the Email");
				pstmt.setString(3, sc.nextLine());

				System.out.println("Enter the Course");
				pstmt.setString(4, sc.nextLine());

		
				pstmt.addBatch();
			}

			int ar[]=pstmt.executeBatch();
			for(int i=0;i<ar.length;i++) {
				n=n+ar[i];
			}
			System.out.println(n + " of row affected");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	//using ResultSetMetaData
	public static void fetchMetaData() {
		Connection con;
		Statement stmt;
		ResultSet res;

		try {
			con = connect();
			stmt = con.createStatement();
			res = stmt.executeQuery("SELECT * FROM student");
			rsmd=res.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.println(rsmd.getColumnName(i)+" "+ rsmd.getColumnTypeName(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	//using CallableStatement
	public static void fetchDataUsingStoreProcedure() {
		Connection con;
		
		ResultSet res;

		try {
			con = connect();
			String sql="{call getAllStudents()}";
			cstmt=con.prepareCall(sql);
			res=cstmt.executeQuery();
			System.out.println("Student Records:");
			while (res.next()) {
				System.out.println("Student id: " + res.getInt("sid") + " " + "Student name: " + res.getString("sname")
						+ " " + "Student Email: " + res.getString("scourse") + " " + "Course taken: "
						+ res.getString("semail"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	public static void fetchSpecificDataUsingStoreProcedure() {
		Connection con;
		
		ResultSet res;

		try {
			con = connect();
			String sql="{call getOneStudents(?)}";
			cstmt=con.prepareCall(sql);
			
			System.out.println("Enter the ID :");
			cstmt.setInt(1, new Scanner(System.in).nextInt());
			res=cstmt.executeQuery();
			System.out.println("Student Records:");
			while (res.next()) {
				System.out.println("Student id: " + res.getInt("sid") + " " + "Student name: " + res.getString("sname")
						+ " " + "Student Email: " + res.getString("scourse") + " " + "Course taken: "
						+ res.getString("semail"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		}

	

	public static void getCourseEnrollCount() {
		Connection con;
		
		ResultSet res;

		try {
			con = connect();
			String sql="{call getCourseEnrollCount(?,?)}";
			cstmt=con.prepareCall(sql);
			
			System.out.println("Enter the Course name (JFS,PFS,DA) :");
			cstmt.setString(1, new Scanner(System.in).next());
			cstmt.registerOutParameter(2,Types.INTEGER);
			cstmt.execute();
			System.out.println("No of students : "+cstmt.getInt(2));
			
			

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	}
