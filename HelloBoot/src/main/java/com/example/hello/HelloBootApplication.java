package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.hello.auto.Car;
import com.example.hello.auto.MyCar;
import com.example.hello.dao.MemberDao;
import com.example.hello.dto.MemberDto;


/*
 * [객체들 간에 의존 관계를 느슨 하게 하는 방법]
 * 
 * 1.핵심 의존 객체를 직접 생성하지 않고 spring에게 객체 생성과 관리를 맡긴다.
 * 2.필요한 객체가 있으면 spring으로 부터 받아서 사용한다.
 * 3.인터페이스 type을 적극 활용한다.
 *  
 */

@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {
		//run()메소드가 리턴해주는 ApplicationoContext 객체의 참조값을 변수에 담고
		ApplicationContext ctx=SpringApplication.run(HelloBootApplication.class, args);
		//만약에 달리고 싶다면 어떻게 해야할까?
		
		//1. 달리는 기능을 가지고 있는 객체를 생성한다.
		//MyCar car1=new MyCar();
		
		//2. 얻어낸 참조값을 이용해서 달린다. 
		//car1.drive();
		
		//위와 동일한 작업을 spring 프레임 워크를 이용해서 작업해 보기
		
		
		//spring이 관리하는 객체 중에서 Car type 찾아서 가지고 오기
		Car car2=ctx.getBean(Car.class); //동일한 type이 2개 이상 있으면 예외 발생!
		car2.drive();
		
		// spring이 관리하는 객체 중에서 myCar라는 이름의 객체를 찾아서 가져오기
		Car car3=(Car)ctx.getBean("myCar");
		car3.drive();
		
		//single ton 객체이기 때문에 참조값은 같다.
		if(car2==car3) {
			System.out.println("car2와 car3는 같아요");
		}
		
		/*
		 * MemberDao type 객체를 bean으로 만들어 지도록 설정하고 
		 * 여기에서 MemberDao type 객체의 참조값을 얻어와서
		 * insert() 메소드를 호출해보세요
		 */
		
		MemberDao dao=ctx.getBean(MemberDao.class);
		dao.insert(new MemberDto());
		
		
	}

}
