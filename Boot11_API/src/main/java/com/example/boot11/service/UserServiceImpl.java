package com.example.boot11.service;

import java.io.File;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot11.dto.UserDto;
import com.example.boot11.exception.PasswordException;
import com.example.boot11.repository.UserDao;

// 서비스클래스는 @Service 어노테이션을 이용해서 bean 으로 만든다 
@Service
public class UserServiceImpl implements UserService{
	//서비스는 dao 를 이용해서 로직 처리를 한다.
	@Autowired
	private UserDao dao;
	//비밀번호 암호화하는 객체도 bean 으로 등록이 되어 있다.
	@Autowired
	private PasswordEncoder encoder;
	//업로드된 이미지를 저장할 파일시스템 상의 위치 
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void addUser(UserDto dto) {
		//암호화된 비밀 번호를 얻어내서 
		String encodedPwd=encoder.encode(dto.getPassword());
		//dto 에 덮어쓰기 한다음
		dto.setPassword(encodedPwd);
		//일반 사용자라는 의미에서 role 에 "USER" 를 넣어준다.
		dto.setRole("USER");
		//DB 에 저장한다.
		dao.insert(dto);
	}

	@Override
	public void getInfo(Model model) {
		//로그인된 userName
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//사용자 정보를 읽어와서 
		UserDto dto=dao.getData(userName);
		//Model 객체에 담는다
		model.addAttribute("dto", dto);
	}

	@Override
	public void updateUser(UserDto dto) {
		MultipartFile image=dto.getImage();
		//만일 선택한 프로필 이미지가 있다면 
		if(image.getSize() != 0) {
			//저장할 파일의 이름 겹치지 않는 유일한 문자열로 얻어내기
			String saveFileName=UUID.randomUUID().toString();
			//저장할 파일의 전체 경로 구성하기
			String filePath=fileLocation+File.separator+saveFileName;
			try {
				//업로드된 파일을 이동시킬 목적지 File 객체
				File f=new File(filePath);
				//MultipartFile 객체의 메소드를 통해서 실제로 이동시키기(전송하기)
				dto.getImage().transferTo(f);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//UserDto 에 저장된 이미지의 이름을 넣어준다.
			dto.setProfile(saveFileName);
		}
		//로그인된 userName 도 dto 에 담아준다 
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUserName(userName);
		
		//dao 를 이용해서 수정반영한다
		dao.update(dto);
	}

	@Override
	public void updatePassword(UserDto dto) {
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//기존의 비밀번호를 읽어와서 
		String password=dao.getData(userName).getPassword();
		//입력한 비밀번호와 비교
		boolean isValid=BCrypt.checkpw(dto.getPassword(), password);
		if(!isValid) { //만일 일치 하지 않으면
			throw new PasswordException("기존 비밀번호가 일치하지 않아요!");
		}
		//비밀번호 수정 작업을 한다.
		//새 비밀번호를 암호화해서 
		String encodedPwd=encoder.encode(dto.getNewPassword());
		//dto 에 담는다
		dto.setNewPassword(encodedPwd);
		//userName 도 dto 에 담는다.
		dto.setUserName(userName);
		//DB 에 수정반영
		dao.updatePwd(dto);
		
	}

}