package test.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
	//자신의 참조값을 담을 static 필드
	private static MemberDao dao;
	
	
	//외부에서 객체 생성하지 못하도록 생성자의 접근 지정자를 private로 
	private MemberDao() {}
	
	//자신의 참조값을 리턴해주는 static public(공개)메소드
	public static MemberDao getInstance() {
		//만일 최초의 호출이라면
		if(dao==null) {
			dao=new MemberDao();
		}
		return dao;
	}
	
	//회원 한명의 정보를 저장하고 작업의 성공여부를 리턴해주는 메소드
	public boolean insert(MemberDto dto) {
		Connection conn=null;
		PreparedStatement pstmt= null;
		int rowCount=0;
		
		try {
			conn= new DbcpBean().getConn();
			String sql="INSERT INTO member"
					+"(num,name,addr)"
					+"VALUES(member_seq.NEXTVAL,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAddr());
			rowCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//회원 한명의 정보를 삭제하고 작업의 성공 여부를 리턴해주는 메소드
	public boolean delete(int num) {
		Connection conn=null;
		PreparedStatement pstmt= null;
		int rowCount=0;
		
		try {
			conn= new DbcpBean().getConn();
			String sql="DELETE From member"
					+" WHERE num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rowCount=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//회원 한명의 정보를 수정해주는 메소드
	public boolean update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE member"
					+"SET name=?, addr=?"
					+"WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2,dto.getName() );
			pstmt.setString(3, dto.getAddr());

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
	
	//회원 한명의 정보를 리턴해주는 메소드
	public MemberDto getData(int num) {
		MemberDto dto= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SLECT *"
					+"FROM member"
					+"WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, num);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			if (rs.next()) {
				dto= new MemberDto();
				dto.setNum(num);
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
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
	
	//회원 목록을 리턴해주는 메소드 
	public List<MemberDto> getList(){
		
		List<MemberDto> list=new ArrayList<>();

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			//DbcpBean() 객체를 이용해서 Connection 객체 하나 얻어내기 (Connection Pool 에서 하나 꺼내오기)
			conn=new DbcpBean().getConn();
			//실행할 sql 문
			String sql="SELECT num, name, addr"+
			 	" FROM member"+
				" ORDER BY num ASC";
			pstmt=conn.prepareStatement(sql);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs=pstmt.executeQuery();
			//반복문 돌면서 
			while(rs.next()){
				//MemberDto 객체에 회원 한명, 한명의 정보를 담아서
				MemberDto dto=new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//ArrayList 객체에 누적 시킨다.
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close(); //Connection 객체의 close() 메소드를 호출하면 Pool 에 반납된다.
			}catch(Exception e){}
		}
		return list;
	}
	
	
	
}

