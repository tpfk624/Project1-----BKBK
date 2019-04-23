package com.kitri.Statistics.chart;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kitri.Statistics.StatBookDto;
import com.kitri.Statistics.StatFoodDto;
import com.kitri.Statistics.StatMemberDto;

//**************************************************************


public class StatDao {


	
	// DB����� ����(����)�� ���� ��ü
	Connection conn = null;

	// �������� ����ϴ� state��ü
	PreparedStatement ps = null;

	Statement st = null;
	ResultSet rs = null;

	// ��� ��� ������ ��� (1��) ���� StatMemberDto ��ü
	StatMemberDto statMemberDto = null;
	StatFoodDto statFoodDto = null;
	StatBookDto statBookDto = null;
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//1. ���̴� ��� SELECT
	public Vector<StatMemberDto> selectMemberRanking() {
		
		// ������ ��� (���� ��) ���� StatMemberDto ��ü
				Vector<StatMemberDto> list = new Vector<StatMemberDto>();

				try {
					// DB ����
					conn = getConnection();

					// ������ ����
					String query = "select member_id, name, birth,\r\n" + 
							"    case\r\n" + 
							"        when birth < '79/01/01'\r\n" + 
							"        then '40��'\r\n" + 
							"        when birth < '89/01/01'\r\n" + 
							"        then '30��'\r\n" + 
							"        when birth < '99/01/01'\r\n" + 
							"        then '20��'\r\n" + 
							"        when birth < '09/01/01'\r\n" + 
							"        then '10��'\r\n" + 
							"        else '���'\r\n" + 
							"    end ���ɱ���\r\n" + 
							"from member\r\n" + 
							"order by birth asc";
					ps = conn.prepareStatement(query);

					// *? ����
//					ps.setInt(1, date);
//					ps.setString(2, minor_level);

					// ������ ����
					// select ������ ��, ��ȸ�� ��� ��(ResultSet)�� ����
					rs = ps.executeQuery();
					
					// *insert, delete, update�����Ҷ�, �������� ���� ����� �� ����(int) ����
//					rs = ps.executeUpdate();

					// ��� ����
					while (rs.next()) {
						statMemberDto = new StatMemberDto();
						
						statMemberDto.setMemberId(rs.getString(1));
						statMemberDto.setName(rs.getString(2));
						statMemberDto.setBirth(rs.getString(3));
						statMemberDto.setAge(rs.getString(4));
												

						list.add(statMemberDto);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						dbClose(rs, ps, conn);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				// ��� ����
				return list;
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	//1. ���̴� �׷�������� ChartMember
	public Vector<StatMemberDto> rankingGraph() {
		// ������ ��� (���� ��) ���� StatMemberDto ��ü
		Vector<StatMemberDto> list = new Vector<StatMemberDto>();

		try {
			// DB ����
			conn = getConnection();

			// ������ ����
			String query = "select a.���ɱ���, count(*)                       \r\n" + 
					"from (select member_id, name, birth,case\r\n" + 
					"                                    when birth < '79/01/01'\r\n" + 
					"                                        then '40��'\r\n" + 
					"                                    when birth < '89/01/01'\r\n" + 
					"                                        then '30��'\r\n" + 
					"                                    when birth < '99/01/01'\r\n" + 
					"                                        then '20��'\r\n" + 
					"							        when birth < '09/01/01' \r\n" + 
					"							            then '10��'\r\n" + 
					"							       else '���'\r\n" + 
					"							            end ���ɱ���\r\n" + 
					"							from member \r\n" + 
					"							order by birth asc) a\r\n" + 
					"group by a.���ɱ��� ";
			ps = conn.prepareStatement(query);

			// ������ ����
			// select ������ ��, ��ȸ�� ��� ��(ResultSet)�� ����
			rs = ps.executeQuery();

			// ��� ����
			while (rs.next()) {
				statMemberDto = new StatMemberDto();
				
				statMemberDto.setCustomerAge(rs.getString(1));
				statMemberDto.setCustomerAgeCount(rs.getInt(2));
										

				list.add(statMemberDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				dbClose(rs, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ��� ����
		return list;
		
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//2. ��ǰ�� ���⳻�� BEST 5
		public Vector selectFoodRanking(String startdate, String lastdate) {

			// ������ ��� (���� ��) ���� PosDto ��ü
			Vector list2 = new Vector();


			// String���� �Է��� ��¥�� '���' ���ļ� int�� ��ȯ
			//int date = Integer.parseInt(year.concat(month).concat("01"));

			try {

				// DB ����
				conn = getConnection();

				// ������ ����
				String query = "select row_number()over(order by f.food_price * v.sc desc) as �������, " +
						"f.food_ctg as ��ǰ���� , f.food_name as ��ǰ��, " + 
						"f.food_price as ��ǰ����, v.sc as �Ǹż���, f.food_price * v.sc as �����հ� " + 
						"from food f, (select food_name, sum(order_amt)sc " + 
						"from order_hstr " + 
						"where substr(pay_num, 1,6) between ? and ? " + 
						"group by food_name)v " + 
						"where f.food_name = v.food_name ";

				ps = conn.prepareStatement(query);
				ps.setString(1, startdate);
				ps.setString(2, lastdate);

				// ������ ����
				rs = ps.executeQuery();

				// ��� ����
				while (rs.next()) {

					Vector list = new Vector();
					list.add(rs.getInt(1));
					list.add(rs.getString(2));
					list.add(rs.getString(3));
					list.add(rs.getInt(4));
					list.add(rs.getInt(5));
					list.add(rs.getInt(6));
					
					list2.add(list);
					
				}



			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				try {

					// DB ���� ����
					dbClose(rs, ps, conn);

				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			// ��� ����

			return list2;

		}
	
		/////////////////////////////////////////////////////////////////////////////////////////
		//2. ��ǰ�� �����հ� �׷��� �����
		public Vector<StatFoodDto> productTotalGraph(String startdate, String lastdate) {
			// ������ ��� (���� ��) ���� StatMemberDto ��ü
			Vector<StatFoodDto> list = new Vector<StatFoodDto>();

			try {
				// DB ����
				conn = getConnection();

				// ������ ����
				String query = "select f.food_name as ��ǰ��, f.food_price * v.sc as �����հ�  \r\n" + 
						"from food f, (select food_name, sum(order_amt)sc  \r\n" + 
						"                from order_hstr  \r\n" + 
						"                where substr(pay_num, 1,6) between ? and ? \r\n" + 
						"                group by food_name)v \r\n" + 
						"where f.food_name = v.food_name";
				ps = conn.prepareStatement(query);

				// ������ ����
				// select ������ ��, ��ȸ�� ��� ��(ResultSet)�� ����
				ps.setInt(1, Integer.parseInt(startdate));
				ps.setInt(2, Integer.parseInt(lastdate));
				rs = ps.executeQuery();

				// ��� ����
				while (rs.next()) {
					statFoodDto = new StatFoodDto();
					
					statFoodDto.setProductName(rs.getString(1));
					statFoodDto.setTotalSellPrice(rs.getInt(2));
											

					list.add(statFoodDto);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					dbClose(rs, ps, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// ��� ����
			return list;
			
		}
//		
//		////////////////////////////////////////////////////////////////////////////
//		//3. ������ �α����
//				public Vector selectBookRanking(String startdate, String lastdate) {
//
//					// ������ ��� (���� ��) ���� PosDto ��ü
//					Vector list2 = new Vector();
//
//
//					// String���� �Է��� ��¥�� '���' ���ļ� int�� ��ȯ
//					//int date = Integer.parseInt(year.concat(month).concat("01"));
//
//					try {
//
//						// DB ����
//						conn = getConnection();
//
//						// ������ ����
//						String query = "select row_number()over(order by v.sc desc) as �������, \r\n" + 
//								"       b.book_name as �����̸�, g.genre_name as �����帣, \r\n" + 
//								"        c.book_ctg_name as ��������\r\n" + 
//								"from book b, genre g, category c,(select book_num, count(pay_num)sc \r\n" + 
//								"                                                 from rent_hstr\r\n" + 
//								"                                                 where substr(pay_num, 1,6) between ? and ? \r\n" + 
//								"                                                 group by book_num)v \r\n" + 
//								"where  b.book_num = v.book_num\r\n" + 
//								"    and b.genre_num = g.genre_num\r\n" + 
//								"    and b.book_ctg = c.book_ctg";
//
//						ps = conn.prepareStatement(query);
//						ps.setString(1, startdate);
//						ps.setString(2, lastdate);
//
//						// ������ ����
//						rs = ps.executeQuery();
//
//						// ��� ����
//						while (rs.next()) {
//
//							Vector list = new Vector();
//							list.add(rs.getInt(1));
//							list.add(rs.getString(2));
//							list.add(rs.getString(3));
//							list.add(rs.getString(4));
//				
//							
//							list2.add(list);
//							
//						}
//
//
//
//					} catch (SQLException e) {
//
//						e.printStackTrace();
//
//					} finally {
//
//						try {
//
//							// DB ���� ����
//							dbClose(rs, ps, conn);
//
//						} catch (SQLException e) {
//
//							e.printStackTrace();
//						}
//					}
//
//					// ��� ����
//
//					return list2;
//
//				}		
//		
				
				
				/////////////////////////////////////////////////////////////////////////////////////////
				//3. ������ �α���� �׷��� �����
				public Vector<StatBookDto> BookTotalGraph(String startdate, String lastdate) {
					// ������ ��� (���� ��) ���� StatMemberDto ��ü
					Vector<StatBookDto> list = new Vector<StatBookDto>();

					try {
						// DB ����
						conn = getConnection();

						// ������ ����
						String query = "select v.sc as ���ⰳ��,b.book_name as �����̸�\r\n" + 
								"from book b, genre g, category c,(select book_num, count(pay_num)sc \r\n" + 
								"                                                 from rent_hstr\r\n" + 
								"                                                 where substr(pay_num, 1,6) between ? and ?\r\n" + 
								"                                                 group by book_num)v \r\n" + 
								"where  b.book_num = v.book_num\r\n" + 
								"    and b.genre_num = g.genre_num\r\n" + 
								"    and b.book_ctg = c.book_ctg";
						ps = conn.prepareStatement(query);

						// ������ ����
						// select ������ ��, ��ȸ�� ��� ��(ResultSet)�� ����
						ps.setInt(1, Integer.parseInt(startdate));
						ps.setInt(2, Integer.parseInt(lastdate));
						rs = ps.executeQuery();

						// ��� ����
						while (rs.next()) {
							statBookDto = new StatBookDto();
							
							statBookDto.setTotalcount(rs.getInt(1));
							statBookDto.setBookName(rs.getString(2));
													
							list.add(statBookDto);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							dbClose(rs, ps, conn);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					// ��� ����
					return list;
					
				}				
				
				
	

	/////////////////////////////////////////////////// [DB ���� �� ���� �޼ҵ�]/////////////////////////////////////////////////// ////////////////////////////////////////////////
	// <DB ����> �޼ҵ� : ����� DB ���¸� ������
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user = "bkbktest";
			String pw = "bkbktest";
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
