package test.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dept.dto.DeptDto;
import test.util.DbcpBean;

public class DeptDao {
	//자신의 참조값을 담을 static 필드
	private static DeptDao dao;
	
	private DeptDao() {};
	
	public static DeptDao getInstance() {
		//만일 최초의 호출이라면
		if(dao==null) {
			dao=new DeptDao();
		}
		return dao;
	}

	
	//부서 하나의 정보를 저장하고 작업의 성공여부를 리턴해주는 메소드
	public boolean insert(DeptDto dto) {
		Connection conn=null;
		PreparedStatement pstmt= null;
		int rowCount=0;
		
		try {
			conn= new DbcpBean().getConn();
			String sql="INSERT INTO dept"
					+" (deptno,dname,loc)"
					+" VALUES(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
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
	
	// 부서의 정보를 삭제하고 작업의 성공 여부를 리턴해주는 메소드
	public boolean delete(int deptno) {
		Connection conn=null;
		PreparedStatement pstmt= null;
		int rowCount=0;
		
		try {
			conn= new DbcpBean().getConn();
			String sql="DELETE From dept"
					+" WHERE deptno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
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
		
	//부서 하나의 정보를 수정해주는 메소드
	public boolean update(DeptDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "UPDATE dept"
					+" SET dname=?, loc=?"
					+" WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setInt(3, dto.getDeptno());
			pstmt.setString(1,dto.getDname());
			pstmt.setString(2, dto.getLoc());

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
	public DeptDto getData(int deptno) {
		DeptDto dto= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "SELECT dname,loc"
					+" FROM dept"
					+" WHERE deptno=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, deptno);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//만일 select된 row가 있다면...
			if (rs.next()) {
				//MemberDto 객체를 생성해서
				dto= new DeptDto();
				//회원 한명의 정보를 담는다.
				dto.setDeptno(deptno);
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
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
	
	//부서 목록을 리턴해주는 메소드 
	public List<DeptDto> getList(){
		
		List<DeptDto> list=new ArrayList<>();

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			//DbcpBean() 객체를 이용해서 Connection 객체 하나 얻어내기 (Connection Pool 에서 하나 꺼내오기)
			conn=new DbcpBean().getConn();
			//실행할 sql 문
			String sql="SELECT deptno, dname, loc"+
			 	" FROM dept"+
				" ORDER BY deptno ASC";
			pstmt=conn.prepareStatement(sql);
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs=pstmt.executeQuery();
			//반복문 돌면서 
			while(rs.next()){
				//DeptDto객체에 회원 한명, 한명의 정보를 담아서
				DeptDto dto=new DeptDto();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
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
