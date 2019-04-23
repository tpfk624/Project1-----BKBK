package com.kitri.Book.rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



//유저등록할때 받아야하는 값
//유저 ID, 유저 패스워드, 이름 , 권한

public class BookDAO {

   BookDTO dto;
   DBManager dbm;
   
   // DB연결시 필요
   Connection conn = null;
   PreparedStatement ps = null;
   ResultSet rs = null;

   public BookDAO() {

   }
   
   // BookSearch 부분
   public Vector<BookDTO> select(String bookname) {
      Vector<BookDTO> list = new Vector<BookDTO>();
      
      conn = dbm.getConnection();
      
      String select = "SELECT book_name, author, publisher " + "FROM book WHERE book_name like '%" + bookname +"%'";

      try {

         ps = conn.prepareStatement(select);
         rs = ps.executeQuery();

         while (rs.next()) {
            String book_name = rs.getString(1);
            String author = rs.getString(2);
            String publisher = rs.getString(3);
//            String possible = rs.getString(4);

            dto = new BookDTO();

            dto.setBookName(book_name);
            dto.setAuthor(author);
            dto.setPublisher(publisher);
//            dto.setRentState(possible);

            list.add(dto);
         }
      } catch (SQLException e1) {
         System.out.println("테이블 출력 오류");
         e1.printStackTrace();
      } finally {
         try {
            dbm.dbClose(ps, conn);
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return list;
   }


   public Vector<BookDTO> BookList() {
      // 보여지는 테이블에 넣는 값들 : 유저코드, 패스워드, 아이디, 권한, 이름
      Vector<BookDTO> list = new Vector<BookDTO>();
      conn = dbm.getConnection();
      String select = "select book_name, author, publisher " + "from book";
      try {
         ps = conn.prepareStatement(select);
         rs = ps.executeQuery();
         while (rs.next()) {
            String book_name = rs.getString(1);
            String author = rs.getString(2);
            String publisher = rs.getString(3);
//            if(rs.getString("rent_state").equals("0"))
               
            dto = new BookDTO();
            dto.setBookName(book_name);
            dto.setAuthor(author);
            dto.setPublisher(publisher);
//            dto.setRentState(possible);
            list.add(dto);
//            row.clear();
         }

      } catch (SQLException e1) {
         System.out.println("테이블 출력 오류");
         e1.printStackTrace();
      } finally {
         try {
            dbm.dbClose(ps, conn);
         } catch (SQLException e) {

            e.printStackTrace();
         }
      }
      return list;

   }



}