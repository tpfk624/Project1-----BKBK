package project;

import java.sql.*;


public class OracleTest {
    public static void main(String args[]) {


        Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null; // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null; // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü


        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.

            String select = "SELECT * FROM EMPLOYEES";
//          String insert = "INSERT INTO EMP_50 VALUES(123,'A', 2000, 'Shipping')";
            String delete = "delete from emp_50 where eid = 123";
//          String update = "update emp_50 set name='B' where eid = 123";


            conn = DBConnection.getConnection(); // DB�� ����Ǿ��ִ� Ŭ���� ȣ��
            pstm = conn.prepareStatement(select); // DB���̺� select
            rs = pstm.executeQuery();


            // insert, delete, update ����� �� �ʿ��� �޼ҵ�
            Statement st = conn.createStatement();
            int result = st.executeUpdate(delete);


            System.out.println("employee_id, first_name");
            System.out.println("============================================");


            // DB ���̺� �ҷ����� �޼ҵ�
//            while (rs.next()) {
//                int employee_id = rs.getInt(1);
//                String first_name = rs.getString(2);
//                // int empno = rs.getInt("empno"); ���� ��� �÷� �̸��� ��� �ȴ�.
////                String job = rs.getString(3);
////                int mgr = rs.getInt(4);
////                java.sql.Date hiredate = rs.getDate(5); // Date Ÿ�� ó��
////                int sal = rs.getInt(6);
////                int comm = rs.getInt(7);
////                int deptno = rs.getInt(8);
//
//                String result = employee_id + " " + first_name;
//                System.out.println(result);
//            }


        } catch (SQLException sqle) {
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();


        } finally {
            // DB ������ �����Ѵ�.
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }


        }
    }
}