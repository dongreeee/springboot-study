package com.apple.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.InsufficientResourcesException;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

		String lover = "정재현";
		System.out.println(lover);

		int age = 20;
		System.out.println(age);
//		class는 변수, 함수 보관함임
//		Q.클래스 안에 있는 변수함수 쓰려면 ? class를 복사해서 써야함
//		중요한 변수, 함수 원본 지킬 수 있음
		Test test = new Test();
//		test -> object
//		System.out.println(test.name);
//		test.hello();

		Friend friend = new Friend("lee");

		var a = new Test();
		a.setName("홍길동");
		a.setAge(60);
		System.out.println(a.getAge());
		a.Plus1();
		a.myAge(12);
		System.out.println(a.getAge());
		a.myAge(-20);
		System.out.println(a.getAge());

		System.out.println(a.getAge());

	}

}

@Getter
@Setter
class Test{
//	String name = "kim";
//	void hello(){
//		System.out.println("안녕");
//	}

	private String name;
	private Integer age;

	public void Plus1() {
		this.age = this.age+1;
	}

	public void myAge(Integer a) {
		if(a > 0 && a < 100){
			this.age = a;
		}
	}


}

class Friend{
	String name = "park";
	int age = 20;
//	constructor
	Friend(String a){
		this.name = a;
//		새로 생성될 object
	}
}
