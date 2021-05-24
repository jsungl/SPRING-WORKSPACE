package com.kh.spring.common.aop.test;

public class AopTest {

	Foo foo = new FooProxy(new FooImpl());
	
	public static void main(String[] args) {
		
		
		//new AopTest().fooTest();
		new AopTest().fooNameTest();
		
	}
	
	public void fooTest() {
		foo.sayHello();
	}

	public void fooNameTest() {
		String name = foo.getName();
		System.out.println(name);
	}
}
