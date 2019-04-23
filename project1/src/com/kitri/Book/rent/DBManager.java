package com.kitri.Book.rent;
import java.sql.*;

public class DBManager {
	// <DB ����> �޼ҵ� : ����� DB ���¸� ������
		public static Connection getConnection() {
			Connection conn = null;
			try {
				String user = "project1";
				String pw = "project1";
				String url = "jdbc:oracle:thin:@localhost:1521:orcl";

				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, user, pw);

				System.out.println("Database�� ����Ǿ����ϴ�.\n");

			} catch (ClassNotFoundException cnfe) {
				System.out.println("DB ����̹� �ε� ���� :" + cnfe.toString());
			} catch (SQLException sqle) {
				System.out.println("DB ���ӽ��� : " + sqle.toString());
			} catch (Exception e) {
				System.out.println("Unkonwn error");
				e.printStackTrace();
			}
			return conn;
		}

		
		// *rs : ResultSet
		// st : Statement
		// ps : PreparedStatement
		// conn : Connection

		// <DB ���� ���� 1> �޼ҵ� : ps + conn
		public static void dbClose(PreparedStatement ps, Connection conn) throws SQLException {
			if (ps != null)
				ps.close();
		
			if (conn != null)
				conn.close();
			
			
			System.out.println("�ڿ��ݳ�, DB Close!!");
		}

		// <DB ���� ���� 2> �޼ҵ� : st + conn
		public static void dbClose(Statement st, Connection conn) throws SQLException {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
			System.out.println("�ڿ��ݳ�, DB Close!!");
		}

		// <DB ���� ���� 3> �޼ҵ� : rs + ps + conn
		public static void dbClose(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("�ڿ��ݳ�, DB Close!!");
		}

		// <DB ���� ���� 4> �޼ҵ� : rs + st + conn
		public static void dbClose(ResultSet rs, Statement st, Connection conn) throws SQLException {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
			System.out.println("�ڿ��ݳ�, DB Close!!");
		}
}