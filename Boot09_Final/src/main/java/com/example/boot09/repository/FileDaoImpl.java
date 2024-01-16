package com.example.boot09.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot09.dto.FileDto;
@Repository
public class FileDaoImpl implements FileDao {
   @Autowired 
   private SqlSession session;
   
   @Override
   public List<FileDto> getList(FileDto dto) {
      //dto안에 넣어줄 startRowNum 과 endRowNum을 넣을 예정
      List<FileDto> list=session.selectList("file.getList",dto);
      return list;
   }


	
	@Override
	public void insert(FileDto dto) {
		session.insert("file.insert",dto);
		
	}
	
	@Override
	public FileDto getData(int num) {
		
		return session.selectOne("file.getData",num);
	}
	
	@Override
	public void delete(int num) {
		session.delete("file.delete", num);
		
	}
	
	@Override
	public int getCount(FileDto dto) {
		
		return session.selectOne("file.getCount",dto);
	}
   
   
   
}