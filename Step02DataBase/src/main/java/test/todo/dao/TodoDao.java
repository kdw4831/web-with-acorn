package test.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.todo.dto.TodoDto;
import test.util.DbcpBean;

public class TodoDao {
	//3. dao를 static으로 선언
	private static TodoDao dao;
	//1. 생성자 호출을 막아주고 
	private TodoDao() {};
	
	//2.getInstance 메소드를 불러온다.
	public static TodoDao getInstance() {
		//조건, 최초 호출이라면 
		if(dao==null) {
			dao=new TodoDao();
		}
		return dao;
	}
	
	// 이 구조를 싱글톤 패턴이라고 한다. 
	// 생성자를 한번만 호출할 수 있게 해주는 것
	
	
	
	//할일 하나를 추가하고 작업의 성공여부를 리턴
	public boolean insert(TodoDto dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		
		try {
			conn=new DbcpBean().getConn();
			String sql="insert into todolist"
					+ " (num,todo,done)"
					+ " values(todo_seq.NEXTVAL,?,false)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTodo());
			rowCount=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)conn.close();
			}catch(Exception e) {}
			
		}
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean delete(int num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int rowCount=0;
		
		try {
			conn=new DbcpBean().getConn();
			String sql="delete from todolist"
					+ "where num=?";
			pstmt=conn.prepareStatement(sql);
			rowCount=pstmt.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {};
		}
		
		if(rowCount>0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean update(TodoDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "update todolist"
					+ "set todo=?"
					+ "where num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할 내용이 있으면 바인딩
			pstmt.setString(1, dto.getTodo());
			pstmt.setInt(2, dto.getNum());
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
	
	
	public TodoDto getData(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		TodoDto dto=null;
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "select (num,todo,done)"
					+ "from todolist"
					+ "where num=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			pstmt.setInt(1, dto.getNum());

			
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			if (rs.next()) {
				 dto= new TodoDto();
				 dto.setNum(num);
				 dto.setTodo(rs.getString("todo"));
				 dto.setDone(rs.getString("done"));
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
			return dto;
		}
	}
	
	
	public List<TodoDto> getList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TodoDto> list= new ArrayList<TodoDto>();
		try {
			conn = new DbcpBean().getConn();
			//실행할 sql 문
			String sql = "select * from todolist";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 여기서 한다.
			
			//query 문 수행하고 결과(ResultSet) 얻어내기
			rs = pstmt.executeQuery();
			//반복문 돌면서 
			while (rs.next()) {
				TodoDto dto= new TodoDto();
				dto.setNum(rs.get)
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
			return list;
		}
	}

	
	
	
	
}



























