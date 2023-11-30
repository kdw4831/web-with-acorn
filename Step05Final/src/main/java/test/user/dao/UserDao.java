package test.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.user.dto.UserDto;
import test.util.DbcpBean;

/*
 * 가입 회원 정보를 insert, update, delete, select 할 single ton Dao 만들기
 */

public class UserDao {
	//자신의 참조값을 저장할 필드
	private static UserDao dao;
	//외부에서 객체 생성하지 못하도록
	private UserDao() {}
	//참조값을 리턴해주는 공개 static 메소드 만들기
	public static UserDao getInstance() {
		if(dao==null) {
			dao=new  UserDao();
			
		}
		return dao;
	}
	
	
	// 아이디를 이용해서 회원 한명의 정보를 저장하는 메소드
	public UserDto getData(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDto dto=null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT pwd,email,profile,regdate "
					+ " FROM user_info"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setString(1, id);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			dto = new UserDto();
			//조건 
			if (rs.next()) {
			dto.setId(id);
			dto.setPwd(rs.getString("pwd"));
			dto.setEmail(rs.getString("email"));
			dto.setProfile(rs.getString("profile"));
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
		return dto; // null이 리턴될수도 있다.(존재하지 않은 id라면?)
	}
	
	
	//회원정보를 DB에 저장하고 성공여부를 리턴하는 메소드
	public boolean insert(UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "INSERT INTO user_info"
					+ " (id,pwd,email,regdate)"
					+ " VALUES(?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());

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
	}
	
	
	
	
	
}
