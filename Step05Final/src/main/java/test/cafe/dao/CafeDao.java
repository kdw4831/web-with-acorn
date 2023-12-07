package test.cafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.cafe.dto.CafeDto;
import test.util.DbcpBean;

public class CafeDao {
   public static CafeDao dao;
   
   private CafeDao(){};
   
   public static CafeDao getInstance() {
      if(dao==null) {
         dao=new CafeDao();
      }
      
      return dao;
   }

   //전체 글의 갯수를 리턴하는 메소드 ROWNUM중 가장 큰값이 글의 갯수지?
   //그래서 max(rowNum)으로 가장 높은 행을 구하고 count로 이름을 바꾼겨
   public int getCount() {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int count=0;
	try {
		conn = new DbcpBean().getConn();
		//실행할 sql 문
		String sql = "SELECT MAX(ROWNUM) AS count"
				+ " FROM board_cafe";
		pstmt = conn.prepareStatement(sql);
		//? 에 바인딩할 내용이 있으면 여기서 한다.

		//query 문 수행하고 결과(ResultSet) 얻어내기
		rs = pstmt.executeQuery();
		//반복문 돌면서 
		if (rs.next()) {
			count=rs.getInt("count");
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
		} catch (Exception e) {
		}
	}
	return count;
   }
   
   //글 저장
   public boolean insert(CafeDto dto) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int rowCount = 0;
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "INSERT INTO board_cafe"
               + " (num,writer,title,content,viewCount,regdate)"
               + " values(board_cafe_seq.NEXTVAL,?,?,?,?,SYSDATE)";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩 할 내용이 있으면 바인딩
         pstmt.setString(1,dto.getWriter());
         pstmt.setString(2,dto.getTitle() );
         pstmt.setString(3, dto.getContent());
         pstmt.setInt(4, dto.getViewCount());
         rowCount = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
         }
      }
      if (rowCount > 0) {
         return true;
      } else {
         return false;
      }
   };
   
   //글 삭제하기
   public boolean delete(int num) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int rowCount = 0;
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "DELETE FROM board_cafe"
               + " WHERE num=?";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩 할 내용이 있으면 바인딩
         pstmt.setInt(1, num);
         rowCount = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
         }
      }
      if (rowCount > 0) {
         return true;
      } else {
         return false;
      }
   };
   
   //글 수정하기
   public boolean update(CafeDto dto) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      int rowCount = 0;
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "UPDATE board_cafe"
               + "   SET title=?,content=?,viewCount=?"
               + " WHERE num=?";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩 할 내용이 있으면 바인딩
         pstmt.setString(1, dto.getTitle());
         pstmt.setString(2, dto.getContent());
         pstmt.setInt(3, dto.getViewCount());
         pstmt.setInt(4, dto.getNum());
         rowCount = pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
         }
      }
      if (rowCount > 0) {
         return true;
      } else {
         return false;
      }
   };

   
   //글목록 하나 가져오기 content 불러오기용
   public CafeDto getdata( int num){
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      CafeDto dto=null;
      
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "SELECT num,writer,title,content,viewCount,regdate"
               + " FROM board_cafe"
               + " WHERE num=?";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩할 내용이 있으면 여기서 한다.
         pstmt.setInt(1, num);
         //query 문 수행하고 결과(ResultSet) 얻어내기
         rs = pstmt.executeQuery();
         //반복문 돌면서 
         if(rs.next()) {
            dto= new CafeDto();
            dto.setNum(rs.getInt("num"));
            dto.setWriter(rs.getString("writer"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setViewCount(rs.getInt("viewCount"));
            dto.setRegdate(rs.getString("regdate"));
            
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
         } catch (Exception e) {
         }
      }
      return dto;
   }
   
   //범위를 설정한 글목록 전체 불러오기
   public List<CafeDto> getList(int start, int end){
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<CafeDto> list= new ArrayList<CafeDto>();
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql ="SELECT *"
					+ " FROM"
					+ "	(SELECT result1.*,ROWNUM AS rnum "
					+ "	FROM"
					+ "		(SELECT num,writer,title,content,viewCount,regdate"
					+ "		FROM board_cafe"
					+ "		ORDER BY num DESC)result1)"
					+ " WHERE rnum BETWEEN ? AND ?";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩할 내용이 있으면 여기서 한다.
         pstmt.setInt(1, start);
         pstmt.setInt(2, end);
         //query 문 수행하고 결과(ResultSet) 얻어내기
         rs = pstmt.executeQuery();
         //반복문 돌면서 
         while (rs.next()) {
            CafeDto dto= new CafeDto();
            dto.setNum(rs.getInt("num"));
            dto.setWriter(rs.getString("writer"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setViewCount(rs.getInt("viewCount"));
            dto.setRegdate(rs.getString("regdate"));
            list.add(dto);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
         } catch (Exception e) {
         }
      }
      return list;
   }
   
   //카페 글목록 전체 가져오기
   public List<CafeDto> getList(){
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<CafeDto> list= new ArrayList<CafeDto>();
      try {
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "SELECT num,writer,title,content,viewCount,regdate"
               + " from board_cafe";
         pstmt = conn.prepareStatement(sql);
         //? 에 바인딩할 내용이 있으면 여기서 한다.
         
         //query 문 수행하고 결과(ResultSet) 얻어내기
         rs = pstmt.executeQuery();
         //반복문 돌면서 
         while (rs.next()) {
            CafeDto dto= new CafeDto();
            dto.setNum(rs.getInt("num"));
            dto.setWriter(rs.getString("writer"));
            dto.setTitle(rs.getString("title"));
            dto.setContent(rs.getString("content"));
            dto.setViewCount(rs.getInt("viewCount"));
            dto.setRegdate(rs.getString("regdate"));
            list.add(dto);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
         } catch (Exception e) {
         }
      }
      return list;
   }

}