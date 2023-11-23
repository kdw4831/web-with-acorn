package test.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.guest.dto.GuestDto;
import test.util.DbcpBean;

public class GuestDao {
	//자신의 참조값을 저장할 static 필드
	private static GuestDao dao;
	
	//static 초기화 블럭(이 클래스가 최초로 사용될 때 오직 한번만 수행된다.)
	static {
		dao=new GuestDao();
	}
	
	//외부에서 객체 생성하지 못하도록
	private GuestDao() {}
	
	//자신의 참조값을 리턴해줄 static 메소드
	public static GuestDao getInstance() {
		return dao;
	}
	/*
	 * 글 번호는 board_guest_seq.NEXTVAL 해서 부여하세요
	 * 글의 등록 날짜는 SYSDATE로  등록하세요
	 * 글 수정시 날짜는 수정하지 마세요
	 */
	
	public boolean insert(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "insert into board_guest"
					+ " (num,writer,content,pwd,regdate)"
					+ " values(board_guest_seq.NEXTVAL,?,?,?,SYSDATE)";
					
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getPwd());
			

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
	
	public boolean update(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "update board_guest"
					+ " set writer=?,content=? "
					+ " where num=? AND pwd=?";
					
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			
			pstmt.setInt(3, dto.getNum());
			pstmt.setString(4, dto.getPwd());
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
	
	
	public boolean delete(GuestDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "delete from board_guest"
					+ " where num=? AND pwd=?";
					
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getPwd());
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
	
	public GuestDto getData(int num) {
		GuestDto dto=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "select writer,content,pwd,regdate "
					+ " from board_guest"
					+ " where num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//만일 select 된 row 가 있다면 
			if (rs.next()) {// 커서를 한칸 내려서
				//커서가 위치한 곳의 글정보를 얻어와서 GuestDto 객체에 담는다.
				dto =new GuestDto();
				dto.setNum(num);
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				//date타입 임에도 불구하고 string으로 불러온다.
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
			} catch (Exception e) {}
		}
		return dto;
	}
	
	public List<GuestDto> getList(){
		//글 정보를 누적시킬 객체를 생성
		List<GuestDto> list= new ArrayList<GuestDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "select num,writer,content,pwd,regdate"
					+ " from board_guest"
					+ " order by num desc";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			//없다
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			while (rs.next()) {
				//글하나 하나의 정보를 GuestDto에 담고
				GuestDto dto =new GuestDto();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setPwd(rs.getString("pwd"));
				dto.setRegdate(rs.getString("regdate"));
				//list에 누적시킨다.
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
