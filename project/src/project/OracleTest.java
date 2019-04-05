package project;

import java.sql.*;


public class OracleTest {
    public static void main(String args[]) {


        Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null; // SQL 문을 나타내는 객체
        ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체


        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.

            String select = "SELECT * FROM EMPLOYEES";
//          String insert = "INSERT INTO EMP_50 VALUES(123,'A', 2000, 'Shipping')";
            String delete = "delete from emp_50 where eid = 123";
//          String update = "update emp_50 set name='B' where eid = 123";


            conn = DBConnection.getConnection(); // DB에 연결되어있는 클래스 호출
            pstm = conn.prepareStatement(select); // DB테이블 select
            rs = pstm.executeQuery();


            // insert, delete, update 사용할 때 필요한 메소드
            Statement st = conn.createStatement();
            int result = st.executeUpdate(delete);


            System.out.println("employee_id, first_name");
            System.out.println("============================================");


            // DB 테이블 불러오는 메소드
//            while (rs.next()) {
//                int employee_id = rs.getInt(1);
//                String first_name = rs.getString(2);
//                // int empno = rs.getInt("empno"); 숫자 대신 컬럼 이름을 적어도 된다.
////                String job = rs.getString(3);
////                int mgr = rs.getInt(4);
////                java.sql.Date hiredate = rs.getDate(5); // Date 타입 처리
////                int sal = rs.getInt(6);
////                int comm = rs.getInt(7);
////                int deptno = rs.getInt(8);
//
//                String result = employee_id + " " + first_name;
//                System.out.println(result);
//            }


        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();


        } finally {
            // DB 연결을 종료한다.
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